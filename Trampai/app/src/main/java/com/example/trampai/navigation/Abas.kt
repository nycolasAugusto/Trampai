package com.example.trampai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.trampai.MeuViewModel
import com.example.trampai.TelaBuscaServicos
import com.example.trampai.TelaCriarServico
import com.example.trampai.TelaHome
import com.example.trampai.TelaPerfil
import com.example.trampai.TelaServicoRelampago

// REGRA DE OURO:
//  - navInterno    -> navegar entre ABAS (barra inferior continua visível)
//  - navController -> navegar para telas CHEIAS (Descrição, Propostas)

@Composable
fun AbaInicio(
    navController: NavHostController,
    navInterno: NavHostController,
    viewModel: MeuViewModel
) {
    TelaHome(
        servicos = viewModel.servicos,
        aoRemover = { viewModel.removerServico(it) },
        irParaBusca = { navInterno.irParaAba(Rotas.ABA_BUSCA) },
        irParaPerfil = { navInterno.irParaAba(Rotas.ABA_PERFIL) },
        irParaUrgente = { navInterno.irParaAba(Rotas.ABA_URGENTE) },
        irParaDescricao = { id -> navController.navigate(Rotas.descricao(id)) }
    )
}

@Composable
fun AbaBusca(navController: NavHostController, navInterno: NavHostController) {
    TelaBuscaServicos(
        voltarParaInicio = { navInterno.irParaAba(Rotas.ABA_INICIO) },
        aoSelecionarCategoria = { navController.navigate(Rotas.PROPOSTAS) }
    )
}

@Composable
fun AbaCriar(navInterno: NavHostController, viewModel: MeuViewModel) {
    TelaCriarServico(
        voltarParaInicio = { navInterno.irParaAba(Rotas.ABA_INICIO) },
        aoPublicar = { servico ->
            viewModel.adicionarServico(servico)
            navInterno.irParaAba(Rotas.ABA_INICIO)
        }
    )
}

@Composable
fun AbaUrgente(navController: NavHostController, navInterno: NavHostController) {
    TelaServicoRelampago(
        voltarParaInicio = { navInterno.irParaAba(Rotas.ABA_INICIO) },
        aoAceitar = { navController.navigate(Rotas.descricao(1)) }
    )
}

@Composable
fun AbaPerfil(navController: NavHostController, navInterno: NavHostController) {
    TelaPerfil(
        voltarParaInicio = { navInterno.irParaAba(Rotas.ABA_INICIO) },
        verPropostas = { navController.navigate(Rotas.PROPOSTAS) }
    )
}