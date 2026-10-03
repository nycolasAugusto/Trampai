package com.example.trampai.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trampai.TelaDescricaoServico
import com.example.trampai.TelaPropostasServidores

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val viewModel: MeuViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Rotas.PRINCIPAL
    ) {

        // Container com as 5 abas (Início, Buscar, Criar, Urgente, Perfil)
        composable(Rotas.PRINCIPAL) {
            MinhaTela(navController, viewModel)
        }

        // Home (card) -> Descrição do Serviço
        composable(Rotas.DESCRICAO) {
            TelaCheia {
                TelaDescricaoServico(
                    voltarParaInicio = { navController.popBackStack() },
                    verPropostas = { navController.navigate(Rotas.PROPOSTAS) }
                )
            }
        }

        // Perfil (card) ou Descrição (botão) -> Propostas de Servidores
        composable(Rotas.PROPOSTAS) {
            TelaCheia {
                TelaPropostasServidores(
                    voltarParaInicio = { navController.popBackStack() },
                    aoSelecionarProfissional = {
                        // PENDENTE: tela de detalhe do profissional (próxima etapa)
                    }
                )
            }
        }
    }
}

// Telas cheias não usam Scaffold, então respeitam as barras do sistema aqui
@Composable
private fun TelaCheia(conteudo: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FD))
            .systemBarsPadding()
    ) {
        conteudo()
    }
}