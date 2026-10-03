package com.example.trampai.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

private data class ItemAba(
    val rota: String,
    val titulo: String,
    val icone: ImageVector
)

// Navega entre abas sem empilhar a mesma aba várias vezes
fun NavHostController.irParaAba(rota: String) {
    navigate(rota) {
        popUpTo(Rotas.ABA_INICIO) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun BottomBarNav(navInterno: NavHostController) {

    val backStackEntry by navInterno.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination?.route

    val itens = listOf(
        ItemAba(Rotas.ABA_INICIO, "Início", Icons.Default.Home),
        ItemAba(Rotas.ABA_BUSCA, "Buscar", Icons.Default.Search),
        ItemAba(Rotas.ABA_CRIAR, "Criar", Icons.Default.Add),
        ItemAba(Rotas.ABA_URGENTE, "Urgente", Icons.Default.Warning),
        ItemAba(Rotas.ABA_PERFIL, "Perfil", Icons.Default.Person)
    )

    NavigationBar {
        itens.forEach { item ->
            NavigationBarItem(
                selected = rotaAtual == item.rota,
                onClick = { navInterno.irParaAba(item.rota) },
                icon = { Icon(imageVector = item.icone, contentDescription = item.titulo) },
                label = { Text(item.titulo) }
            )
        }
    }
}