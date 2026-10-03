package com.example.trampai.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Container das abas: tem a barra inferior e um NavHost próprio (navInterno).
// As telas daqui já trazem o próprio cabeçalho, por isso não há TopAppBar neste Scaffold.
@Composable
fun MinhaTela(navController: NavHostController, viewModel: MeuViewModel) {

    val navInterno = rememberNavController()

    Scaffold(
        bottomBar = { BottomBarNav(navInterno) }
    ) { innerPadding ->

        NavHost(
            navController = navInterno,
            startDestination = Rotas.ABA_INICIO,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Rotas.ABA_INICIO) { AbaInicio(navController, navInterno) }
            composable(Rotas.ABA_BUSCA) { AbaBusca(navController, navInterno) }
            composable(Rotas.ABA_CRIAR) { AbaCriar(navInterno) }
            composable(Rotas.ABA_URGENTE) { AbaUrgente(navController, navInterno) }
            composable(Rotas.ABA_PERFIL) { AbaPerfil(navController, navInterno) }

        }
    }
}