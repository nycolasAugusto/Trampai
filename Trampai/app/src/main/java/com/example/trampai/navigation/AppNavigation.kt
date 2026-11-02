package com.example.trampai.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trampai.data.local.AppDatabase
import com.example.trampai.data.repository.PropostaRepository
import com.example.trampai.data.repository.ServicoRepository
import com.example.trampai.ui.TelaDescricaoServico
import com.example.trampai.ui.TelaPropostasServidores
import com.example.trampai.ui.propostas.PropostasViewModel
import com.example.trampai.ui.propostas.PropostasViewModelFactory
import com.example.trampai.ui.servicos.ServicosViewModel
import com.example.trampai.ui.servicos.ServicosViewModelFactory

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val servicoRepository = ServicoRepository(db.servicoDAO(), db.propostaDAO())
    val propostaRepository = PropostaRepository(db.propostaDAO())

    val servicosViewModel: ServicosViewModel = viewModel(
        factory = ServicosViewModelFactory(servicoRepository)
    )
    val propostasViewModel: PropostasViewModel = viewModel(
        factory = PropostasViewModelFactory(propostaRepository)
    )

    NavHost(
        navController = navController,
        startDestination = Rotas.PRINCIPAL
    ) {

        composable(Rotas.PRINCIPAL) {
            MinhaTela(navController, servicosViewModel)
        }

        composable(
            route = Rotas.DESCRICAO,
            arguments = listOf(navArgument("servicoId") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("servicoId") ?: 0

            LaunchedEffect(id) { servicosViewModel.carregarServico(id) }

            TelaCheia {
                TelaDescricaoServico(
                    servicoId = id,
                    servicosViewModel = servicosViewModel,
                    propostasViewModel = propostasViewModel,
                    voltarParaInicio = { navController.popBackStack() },
                    irParaPropostas = { navController.navigate(Rotas.PROPOSTAS) }
                )
            }
        }

        composable(Rotas.PROPOSTAS) {
            val propostasState by propostasViewModel.uiState.collectAsState()

            TelaCheia {
                TelaPropostasServidores(
                    propostas = propostasState.listaDePropostas,
                    aoRemover = { propostasViewModel.onDelete(it) },
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