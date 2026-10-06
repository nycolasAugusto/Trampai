package com.example.trampai

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

// Hora atual do aparelho, ex: "14:32"
fun horaAtual(): String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

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
    val mensagem: String,
    val hora: String = "",           // preenchida ao enviar
    val valorServico: Double = 0.0,  // valor/hora pedido no serviço (preenchido ao enviar)
    val requerente: String = ""      // quem pediu o serviço (preenchido ao enviar)
) {
    val valorFormatado: String
        get() = if (valorHora % 1.0 == 0.0) "R$ ${valorHora.toInt()}/hr"
        else "R$ ${"%.2f".format(valorHora)}/hr"

    // Negativo = proposta mais barata que o pedido
    val diferenca: Double
        get() = valorHora - valorServico

    val textoDiferenca: String
        get() {
            val d = abs(diferenca)
            val v = if (d % 1.0 == 0.0) "R$ ${d.toInt()}/hr" else "R$ ${"%.2f".format(d)}/hr"
            return when {
                diferenca < 0 -> "$v abaixo do pedido"
                diferenca > 0 -> "$v acima do pedido"
                else -> "Igual ao valor pedido"
            }
        }
}

class MeuViewModel : ViewModel() {

    // ---------- LISTA 1: serviços ----------
    val servicos = mutableStateListOf(
        Servico(1, "Serviço de Limpeza Profunda", "Limpeza", 120.0,
            "Limpeza pesada e organização completa de apartamento de 3 quartos.",
            "Trazer produtos e equipamentos básicos", "Copacabana, RJ", "Clara Mendes"),
        Servico(2, "Reparo Emergencial de Vazamento", "Encanamento", 200.0,
            "Vazamento na cozinha, precisa de atendimento rápido.",
            "Ferramentas de encanamento", "Botafogo, RJ", "Marcos Silva")
    )
    private var proximoIdServico = 3

    fun adicionarServico(servico: Servico) {
        servicos.add(0, servico.copy(id = proximoIdServico++))
    }

    fun removerServico(servico: Servico) {
        servicos.remove(servico)
        propostas.removeAll { it.servicoId == servico.id }
    }

    fun buscarServico(id: Int): Servico? = servicos.find { it.id == id }

    // ---------- LISTA 2: propostas ----------
    val propostas = mutableStateListOf<Proposta>()
    private var proximoIdProposta = 1

    fun adicionarProposta(proposta: Proposta) {
        val servico = buscarServico(proposta.servicoId)
        propostas.add(
            0,
            proposta.copy(
                id = proximoIdProposta++,
                hora = horaAtual(),
                valorServico = servico?.valorHora ?: 0.0,
                requerente = servico?.autor ?: ""
            )
        )
    }

    fun removerProposta(proposta: Proposta) {
        propostas.remove(proposta)
    }

    fun buscarProposta(id: Int): Proposta? = propostas.find { it.id == id }
}