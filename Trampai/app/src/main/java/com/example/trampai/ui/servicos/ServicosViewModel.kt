package com.example.trampai.ui.servicos


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trampai.data.local.Servico
import com.example.trampai.data.repository.ServicoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Nomes de função seguem o padrão "onEvento": cada uma é uma intenção do usuário
class ServicosViewModel(
    private val repository: ServicoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ServicosUiState())
    val uiState: StateFlow<ServicosUiState> = _uiState.asStateFlow()

    init {
        // BUSCAR: se inscreve UMA vez no Flow do Repository. A lista se atualiza
        // sozinha a cada insert/update/delete, sem precisarmos "recarregar" nada.
        viewModelScope.launch {
            try {
                repository.getAllServicos().collect { servicos ->
                    _uiState.update { it.copy(listaDeServicos = servicos) }
                }
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao buscar serviços", e)
            }
        }
    }

    // ---------- campos do formulário ----------
    fun onTituloChange(valor: String) { _uiState.update { it.copy(titulo = valor) } }
    fun onCategoriaChange(valor: String) { _uiState.update { it.copy(categoria = valor) } }
    fun onValorChange(valor: String) { _uiState.update { it.copy(valor = valor) } }
    fun onDescricaoChange(valor: String) { _uiState.update { it.copy(descricao = valor) } }
    fun onRequisitosChange(valor: String) { _uiState.update { it.copy(requisitos = valor) } }
    fun onLocalizacaoChange(valor: String) { _uiState.update { it.copy(localizacao = valor) } }

    // ---------- DETALHE: busca um serviço pelo id que veio na rota ----------
    fun carregarServico(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val servico = repository.buscarPorId(id)
                _uiState.update { it.copy(servicoSelecionado = servico) }
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao buscar serviço $id", e)
            }
        }
    }

    // ---------- EDITAR: joga o serviço no formulário ----------
    fun onEdit(servico: Servico) {
        _uiState.update {
            it.copy(
                servicoEmEdicao = servico,
                titulo = servico.titulo,
                categoria = servico.categoria,
                valor = if (servico.valorHora % 1.0 == 0.0) servico.valorHora.toInt().toString()
                else servico.valorHora.toString(),
                descricao = servico.descricao,
                requisitos = servico.requisitos,
                localizacao = servico.localizacao,
                erro = ""
            )
        }
    }

    // Desiste de criar/editar e limpa o formulário
    fun onCancelar() {
        limparCampos()
    }

    // ---------- REMOVER ----------
    fun onDelete(servico: Servico) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deletar(servico)
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao remover serviço", e)
            }
        }
    }

    // ---------- INSERIR ou ATUALIZAR ----------
    // Devolve true quando o formulário é válido (a tela usa isso para fechar/navegar)
    fun onSave(): Boolean {
        val state = _uiState.value
        val valorNumero = state.valor.replace(",", ".").toDoubleOrNull()

        val mensagemErro = when {
            state.titulo.isBlank() -> "Informe o título do serviço."
            state.categoria.isBlank() -> "Informe a categoria."
            valorNumero == null || valorNumero <= 0 -> "Informe um valor válido, ex: 50 ou 50,50."
            else -> ""
        }

        if (mensagemErro.isNotEmpty() || valorNumero == null) {
            _uiState.update { it.copy(erro = mensagemErro) }
            return false
        }

        val emEdicao = state.servicoEmEdicao
        val servicoParaSalvar = emEdicao?.copy(
            titulo = state.titulo.trim(),
            categoria = state.categoria.trim(),
            valorHora = valorNumero,
            descricao = state.descricao.trim(),
            requisitos = state.requisitos.trim(),
            localizacao = state.localizacao.trim().ifBlank { "Local não informado" }
        ) ?: Servico(
            titulo = state.titulo.trim(),
            categoria = state.categoria.trim(),
            valorHora = valorNumero,
            descricao = state.descricao.trim(),
            requisitos = state.requisitos.trim(),
            localizacao = state.localizacao.trim().ifBlank { "Local não informado" },
            autor = "Alex Souza"
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (emEdicao == null) {
                    repository.inserir(servicoParaSalvar)
                } else {
                    repository.atualizar(servicoParaSalvar)
                    // mantém o detalhe aberto em sincronia com o que foi editado
                    _uiState.update {
                        if (it.servicoSelecionado?.id == servicoParaSalvar.id)
                            it.copy(servicoSelecionado = servicoParaSalvar)
                        else it
                    }
                }
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao salvar serviço", e)
            }
        }

        limparCampos()
        return true
    }

    private fun limparCampos() {
        _uiState.update {
            it.copy(
                titulo = "", categoria = "", valor = "", descricao = "",
                requisitos = "", localizacao = "", erro = "", servicoEmEdicao = null
            )
        }
    }
}