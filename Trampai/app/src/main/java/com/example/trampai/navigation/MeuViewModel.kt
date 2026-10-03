package com.example.trampai.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Categoria(
    val id: Int,
    val nome: String
)

data class Servico(
    val id: Int,
    val titulo: String,
    val categoriaId: Int,
    val valorHora: Double,
    val profissional: String,
    val nota: Double,
    val local: String,
    val descricao: String
)

class MeuViewModel : ViewModel() {

    val categorias = mutableStateListOf(
        Categoria(1, "Limpeza"),
        Categoria(2, "Encanamento"),
        Categoria(3, "Elétrica"),
        Categoria(4, "Pintura"),
        Categoria(5, "Jardinagem"),
        Categoria(6, "Mecânica")
    )

    val servicos = mutableStateListOf(
        Servico(
            id = 1,
            titulo = "Serviço de Limpeza Profunda",
            categoriaId = 1,
            valorHora = 120.0,
            profissional = "Clara Mendes",
            nota = 4.9,
            local = "Copacabana, RJ",
            descricao = "Limpeza completa de apartamentos, com produtos inclusos."
        ),
        Servico(
            id = 2,
            titulo = "Reparo Emergencial de Vazamento",
            categoriaId = 2,
            valorHora = 200.0,
            profissional = "Marcos Silva",
            nota = 4.8,
            local = "Botafogo, RJ",
            descricao = "Atendimento rápido para vazamentos e entupimentos."
        )
    )

    private var proximoIdServico = 3
    private var proximoIdCategoria = 7

    // ----- Serviços -----
    fun adicionarServico(
        titulo: String,
        categoriaId: Int,
        valorHora: Double,
        descricao: String,
        local: String
    ) {
        servicos.add(
            Servico(
                id = proximoIdServico++,
                titulo = titulo,
                categoriaId = categoriaId,
                valorHora = valorHora,
                profissional = "Alex Souza",
                nota = 5.0,
                local = local,
                descricao = descricao
            )
        )
    }

    fun removerServico(servico: Servico) {
        servicos.remove(servico)
    }

    fun buscarServico(id: Int): Servico? = servicos.find { it.id == id }

    // ----- Categorias -----
    fun adicionarCategoria(nome: String) {
        categorias.add(Categoria(proximoIdCategoria++, nome))
    }

    // Ao remover uma categoria, os serviços dela também saem
    fun removerCategoria(categoria: Categoria) {
        servicos.removeAll { it.categoriaId == categoria.id }
        categorias.remove(categoria)
    }

    fun buscarCategoria(id: Int): Categoria? = categorias.find { it.id == id }
}