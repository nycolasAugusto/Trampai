package com.example.trampai.viewmodel.propostas

import com.example.trampai.data.local.Proposta

// Tudo que as telas de proposta precisam para se desenhar
data class PropostasUiState(
    // Lista (tela Propostas de Servidores)
    val listaDePropostas: List<Proposta> = emptyList(),

    // Formulário de envio da proposta (diálogo na Descrição)
    val valor: String = "",
    val mensagem: String = "",
    val erro: String = ""
)