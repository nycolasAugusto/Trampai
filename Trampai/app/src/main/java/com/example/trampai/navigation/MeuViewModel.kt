package com.example.trampai

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Servico(
    val id: Int,
    val titulo: String,
    val categoria: String,
    val valorHora: Double,
    val descricao: String,
    val requisitos: String,
    val localizacao: String,
    val autor: String
) {
    val valorFormatado: String
        get() = if (valorHora % 1.0 == 0.0) "R$ ${valorHora.toInt()}/hr"
        else "R$ ${"%.2f".format(valorHora)}/hr"
}
data class Proposta(
    val id: Int,
    val servicoId: Int,
    val servicoTitulo: String,
    val profissional: String,
    val valorHora: Double,
    val mensagem: String
) {
    val valorFormatado: String
        get() = if (valorHora % 1.0 == 0.0) "R$ ${valorHora.toInt()}/hr"
        else "R$ ${"%.2f".format(valorHora)}/hr"
}

class MeuViewModel : ViewModel() {

    val servicos = mutableStateListOf(
        Servico(1, "Serviço de Limpeza Profunda", "Limpeza", 120.0,
            "Limpeza pesada e organização completa de apartamento de 3 quartos.",
            "Trazer produtos e equipamentos básicos", "Copacabana, RJ", "Clara Mendes"),
        Servico(2, "Reparo Emergencial de Vazamento", "Encanamento", 200.0,
            "Vazamento na cozinha, precisa de atendimento rápido.",
            "Ferramentas de encanamento", "Botafogo, RJ", "Marcos Silva")
    )
    val propostas = mutableStateListOf<Proposta>()
    private var proximoIdProposta = 1

    fun adicionarProposta(proposta: Proposta) {
        propostas.add(0, proposta.copy(id = proximoIdProposta++))
    }

    fun removerProposta(proposta: Proposta) {
        propostas.remove(proposta)
    }

    private var proximoId = 3

    fun adicionarServico(servico: Servico) {
        servicos.add(0, servico.copy(id = proximoId++))
    }

    fun removerServico(servico: Servico) {
        servicos.remove(servico)
        propostas.removeAll { it.servicoId == servico.id }
    }

    fun buscarServico(id: Int): Servico? = servicos.find { it.id == id }
}