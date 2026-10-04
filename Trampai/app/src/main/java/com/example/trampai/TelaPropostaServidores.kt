package com.example.trampai

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val primaryColor = Color(0xFF6366F1)
private val textPrimary = Color(0xFF0F172A)
private val textSecondary = Color(0xFF64748B)

@Composable
fun TelaPropostasServidores(
    propostas: List<Proposta>,
    aoRemover: (Proposta) -> Unit,
    voltarParaInicio: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FD))
            .padding(horizontal = 18.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // CABEÇALHO
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { voltarParaInicio() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color(0xFF1E293B),
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text("Propostas de Servidores", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textPrimary)
                Text("${propostas.size} proposta(s)", fontSize = 13.sp, color = Color(0xFF94A3B8))
            }
        }

        // LISTA REAL DE PROPOSTAS
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (propostas.isEmpty()) {
                item {
                    Text(
                        "Nenhuma proposta ainda. Abra um serviço e toque em Enviar Proposta.",
                        fontSize = 14.sp,
                        color = textSecondary
                    )
                }
            }

            items(propostas, key = { it.id }) { proposta ->
                CardProposta(proposta = proposta, aoRemover = { aoRemover(proposta) })
            }
        }
    }
}

@Composable
private fun CardProposta(proposta: Proposta, aoRemover: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(44.dp).clip(CircleShape).background(primaryColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(proposta.profissional, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = textPrimary)
                    Text("Para: ${proposta.servicoTitulo}", fontSize = 12.sp, color = textSecondary)
                }

                Text(proposta.valorFormatado, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = primaryColor)

                IconButton(onClick = aoRemover) {
                    Icon(Icons.Default.Delete, contentDescription = "Remover proposta", tint = Color(0xFFD32F2F))
                }
            }

            if (proposta.mensagem.isNotBlank()) {
                Text(proposta.mensagem, fontSize = 14.sp, color = Color(0xFF475569))
            }
        }
    }
}