package com.example.trampai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TelaServicoRelampago(
    voltarParaInicio: () -> Unit,
    aoAceitar: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // ÁREA DO MAPA (Simulada)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFE0E0E0)), // Cinza para o mapa
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "📍 Localização em Tempo Real",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
            
            // Botão de fechar/voltar no topo do mapa
            IconButton(
                onClick = voltarParaInicio,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .background(Color.White, shape = CircleShape)
            ) {
                Text("X")
            }
        }

        // PAINEL DE INFORMAÇÕES
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "NOVA SOLICITAÇÃO",
                    fontSize = 12.sp,
                    color = Color(0xFF5A4FCF),
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "Reparo de Chuveiro",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // BOTÃO RECUSAR
                    Button(
                        onClick = voltarParaInicio,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4D4D)),
                        modifier = Modifier.weight(1f).padding(end = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Recusar", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    // BOTÃO ACEITAR
                    Button(
                        onClick = aoAceitar,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                        modifier = Modifier.weight(1f).padding(start = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Aceitar", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }

                // DETALHES DO SERVIÇO
                HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp))

                InfoRow(label = "📍 Distância", value = "0.8 km (5 min)")
                InfoRow(label = "💰 Valor Estimado", value = "R$ 45,00")
                InfoRow(label = "👤 Cliente", value = "Mariana Costa")
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaRelampago() {
    TelaServicoRelampago(voltarParaInicio = {})
}
