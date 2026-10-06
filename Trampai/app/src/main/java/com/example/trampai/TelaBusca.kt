package com.example.trampai

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.ElectricalServices
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Yard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val roxo = Color(0xFF5A4FCF)
private val textoEscuro = Color(0xFF0F172A)
private val textoSuave = Color(0xFF64748B)

// ícone to nome
private val categorias = listOf(
    Icons.Default.CleaningServices to "Limpeza",
    Icons.Default.Plumbing to "Encanamento",
    Icons.Default.ElectricalServices to "Elétrica",
    Icons.Default.FormatPaint to "Pintura",
    Icons.Default.Yard to "Jardinagem",
    Icons.Default.Build to "Mecânica"
)

@Composable
fun TelaBuscaServicos(
    voltarParaInicio: () -> Unit,
    aoSelecionarCategoria: () -> Unit
) {
    var busca by remember { mutableStateOf("") }
    val filtradas = categorias.filter { it.second.contains(busca, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        // CABEÇALHO
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { voltarParaInicio() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = roxo)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text("Buscar Serviços", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = roxo)
                Text("Escolha uma categoria", fontSize = 13.sp, color = textoSuave)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // CAMPO DE BUSCA
        OutlinedTextField(
            value = busca,
            onValueChange = { busca = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("O que você está procurando?", color = Color(0xFF94A3B8)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = roxo) },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = roxo,
                unfocusedBorderColor = Color(0xFFD5D8E0),
                cursorColor = roxo,
                focusedTextColor = textoEscuro,
                unfocusedTextColor = textoEscuro
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // GRADE DE CATEGORIAS
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            if (filtradas.isEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text("Nenhuma categoria encontrada.", color = textoSuave, fontSize = 14.sp)
                }
            }

            items(filtradas) { (icone, nome) ->
                CardCategoria(icone, nome, aoSelecionarCategoria)
            }
        }
    }
}

@Composable
private fun CardCategoria(icone: ImageVector, nome: String, aoClicar: () -> Unit) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .clickable { aoClicar() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(roxo.copy(alpha = 0.1f), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icone, contentDescription = nome, tint = roxo, modifier = Modifier.size(30.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(nome, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textoEscuro)
        }
    }
}