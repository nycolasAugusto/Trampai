package com.example.trampai.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlin.math.abs

@Entity(tableName = "propostas")
data class Proposta(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val servicoId: Int,              // liga a proposta ao serviço dela
    val servicoTitulo: String,
    val profissional: String,
    val valorHora: Double,
    val mensagem: String,
    val hora: String = "",           // preenchida ao enviar
    val valorServico: Double = 0.0,  // valor/hora pedido no serviço (preenchido ao enviar)
    val requerente: String = ""      // quem pediu o serviço (preenchido ao enviar)
)

// Valores calculados: ficam FORA da classe para o Room não confundir com colunas.

val Proposta.valorFormatado: String
    get() = if (valorHora % 1.0 == 0.0) "R$ ${valorHora.toInt()}/hr"
    else "R$ ${"%.2f".format(valorHora)}/hr"

val Proposta.diferenca: Double
    get() = valorHora - valorServico

val Proposta.textoDiferenca: String
    get() {
        val d = abs(diferenca)
        val v = if (d % 1.0 == 0.0) "R$ ${d.toInt()}/hr" else "R$ ${"%.2f".format(d)}/hr"
        return when {
            diferenca < 0 -> "$v abaixo do pedido"
            diferenca > 0 -> "$v acima do pedido"
            else -> "Igual ao valor pedido"
        }
    }