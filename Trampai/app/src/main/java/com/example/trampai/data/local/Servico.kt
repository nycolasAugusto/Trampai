package com.example.trampai.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "servicos")
data class Servico(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val titulo: String,
    val categoria: String,
    val valorHora: Double,
    val descricao: String,
    val requisitos: String,
    val localizacao: String,
    val autor: String
)

// Texto pronto para exibir: "R$ 120/hr" ou "R$ 50,50/hr".
// Fica FORA da classe para o Room não confundir com uma coluna da tabela.
val Servico.valorFormatado: String
    get() = if (valorHora % 1.0 == 0.0) "R$ ${valorHora.toInt()}/hr"
    else "R$ ${"%.2f".format(valorHora)}/hr"