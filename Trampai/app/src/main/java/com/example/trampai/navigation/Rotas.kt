package com.example.trampai.navigation

object Rotas {

    // NavHost de fora (AppNavigation): telas cheias, sem barra inferior
    const val PRINCIPAL = "principal"      // container com as abas (MinhaTela)
    const val PROPOSTAS = "propostas"
    const val DESCRICAO = "descricao"

    // NavHost de dentro (MinhaTela): abas da barra inferior
    const val ABA_INICIO = "aba_inicio"
    const val ABA_BUSCA = "aba_busca"
    const val ABA_CRIAR = "aba_criar"
    const val ABA_URGENTE = "aba_urgente"
    const val ABA_PERFIL = "aba_perfil"
}