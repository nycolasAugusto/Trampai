package com.example.trampai.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trampai.AppDatabase
import com.example.trampai.MeuViewModel
import com.example.trampai.Servico
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

        composable(Rotas.PRINCIPAL) {
            MinhaTela(navController, viewModel)
        }

        composable(
            route = Rotas.DESCRICAO,
            arguments = listOf(navArgument("servicoId") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("servicoId") ?: 0
            val db = AppDatabase.getDatabase(LocalContext.current)

            var servico by remember { mutableStateOf<Servico?>(null) }

            LaunchedEffect(id) {
                servico = viewModel.buscarServicoPorId(db, id)   // dao.buscarPorId(id)
                viewModel.carregarPropostas(db)                  // para contar as propostas
            }

            servico?.let { atual ->
                TelaCheia {
                    TelaDescricaoServico(
                        servico = atual,
                        qtdPropostas = viewModel.propostas.count { it.servicoId == atual.id },
                        voltarParaInicio = { navController.popBackStack() },
                        aoEnviarProposta = { proposta ->
                            viewModel.adicionarProposta(db, proposta)
                            navController.navigate(Rotas.PROPOSTAS)
                        },
                        aoSalvarEdicao = { editado ->
                            servico = editado                       // mostra na hora
                            viewModel.atualizarServico(db, editado) // grava no banco
                        }
                    )
                }
            }
        }

        composable(Rotas.PROPOSTAS) {
            val db = AppDatabase.getDatabase(LocalContext.current)


            LaunchedEffect(Unit) {
                viewModel.carregarPropostas(db)
            }

            TelaCheia {
                TelaPropostasServidores(
                    propostas = viewModel.propostas,
                    aoRemover = { viewModel.removerProposta(db, it) },
                    voltarParaInicio = { navController.popBackStack() }
                )
            }
        }
    }
}


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