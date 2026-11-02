package com.example.trampai.viewmodel.propostas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trampai.data.local.Proposta
import com.example.trampai.data.local.Servico
import com.example.trampai.data.repository.PropostaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Hora atual do aparelho, ex: "14:32"
fun horaAtual(): String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

class PropostasViewModel(
    private val repository: PropostaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PropostasUiState())
    val uiState: StateFlow<PropostasUiState> = _uiState.asStateFlow()

    init {

        viewModelScope.launch {
            try {
                repository.getAllPropostas().collect { propostas ->
                    _uiState.update { it.copy(listaDePropostas = propostas) }
                }
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao buscar propostas", e)
            }
        }
    }

    fun onValorChange(valor: String) { _uiState.update { it.copy(valor = valor) } }
    fun onMensagemChange(mensagem: String) { _uiState.update { it.copy(mensagem = mensagem) } }


    fun onCancelar() {
        limparCampos()
    }

    fun onEnviar(servico: Servico): Boolean {
        val state = _uiState.value
        val valorNumero = state.valor.replace(",", ".").toDoubleOrNull()

        if (valorNumero == null || valorNumero <= 0) {
            _uiState.update { it.copy(erro = "Informe um valor válido, ex: 90 ou 90,50.") }
            return false
        }

        val proposta = Proposta(
            servicoId = servico.id,
            servicoTitulo = servico.titulo,
            profissional = "Alex Souza",
            valorHora = valorNumero,
            mensagem = state.mensagem.trim(),
            hora = horaAtual(),
            valorServico = servico.valorHora,
            requerente = servico.autor
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.inserir(proposta)
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao inserir proposta", e)
            }
        }

        limparCampos()
        return true
    }

    // ---------- REMOVER ----------
    fun onDelete(proposta: Proposta) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deletar(proposta)
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao remover proposta", e)
            }
        }
    }

    private fun limparCampos() {
        _uiState.update { it.copy(valor = "", mensagem = "", erro = "") }
    }
}