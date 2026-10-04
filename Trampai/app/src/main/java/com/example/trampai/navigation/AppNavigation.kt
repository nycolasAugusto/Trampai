package com.example.trampai.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trampai.MeuViewModel
import com.example.trampai.TelaDescricaoServico
import com.example.trampai.TelaPropostasServidores

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val viewModel: MeuViewModel = MeuViewModel()

    NavHost(
        navController = navController,
        startDestination = Rotas.PRINCIPAL
    ) {

        // Container com as 5 abas (Início, Buscar, Criar, Urgente, Perfil)
        composable(Rotas.PRINCIPAL) {
            MinhaTela(navController, viewModel)
        }

        // Home (card) -> Descrição do Serviço
        composable(
            route = Rotas.DESCRICAO,
            arguments = listOf(navArgument("servicoId") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("servicoId") ?: 0
            val servico = viewModel.buscarServico(id)

            if (servico != null) {
                TelaCheia {
                    TelaDescricaoServico(
                        servico = servico,
                        qtdPropostas = viewModel.propostas.count { it.servicoId == servico.id },
                        voltarParaInicio = { navController.popBackStack() },
                        aoEnviarProposta = { proposta ->
                            viewModel.adicionarProposta(proposta)
                            navController.navigate(Rotas.PROPOSTAS)
                        }
                    )
                }
            }
        }

        composable(Rotas.PROPOSTAS) {
            TelaCheia {
                TelaPropostasServidores(
                    propostas = viewModel.propostas,
                    aoRemover = { viewModel.removerProposta(it) },
                    voltarParaInicio = { navController.popBackStack() }
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