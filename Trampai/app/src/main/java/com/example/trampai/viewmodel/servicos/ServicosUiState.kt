package com.example.trampai.viewmodel.servicos


import com.example.trampai.data.local.Servico

// Tudo que as telas de serviço precisam para se desenhar
data class ServicosUiState(
    // Lista (aba Início)
    val listaDeServicos: List<Servico> = emptyList(),

    // Detalhe (tela Descrição): lido do banco pelo id da rota
    val servicoSelecionado: Servico? = null,

    // Formulário: usado para criar (aba Criar) e para editar (diálogo da Descrição)
    val titulo: String = "",
    val categoria: String = "",
    val valor: String = "",
    val descricao: String = "",
    val requisitos: String = "",
    val localizacao: String = "",
    val erro: String = "",
    val servicoEmEdicao: Servico? = null    // null = criando, preenchido = editando
)