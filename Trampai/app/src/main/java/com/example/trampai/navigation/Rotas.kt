package com.example.trampai.navigation

object Rotas {

    // NavHost de fora (AppNavigation): telas cheias
    const val PRINCIPAL = "principal"
    const val PROPOSTAS = "propostas"
    const val DESCRICAO = "descricao/{servicoId}"   // rota com argumento

    // NavHost de dentro (MinhaTela): abas da barra inferior
    const val ABA_INICIO = "aba_inicio"
    const val ABA_BUSCA = "aba_busca"
    const val ABA_CRIAR = "aba_criar"
    const val ABA_URGENTE = "aba_urgente"
    const val ABA_PERFIL = "aba_perfil"

    // Monta a rota preenchendo o id: descricao(3) -> "descricao/3"
    fun descricao(id: Int) = "descricao/$id"
}