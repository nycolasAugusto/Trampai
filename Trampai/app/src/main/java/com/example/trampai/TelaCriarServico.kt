package com.example.trampai

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaCriarServico(voltarParaInicio: () -> Unit, aoPublicar: () -> Unit) {
    // Paleta de cores extraída da imagem
    val corFundo = Color(0xFFF5F5F5)
    val corDestaqueAmarelo = Color(0xFF5A4FCF)
    val corTextoLabel = Color(0xFFE5D5C5)
    val corBordaCaixa = Color(0xFF5A4FCF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corFundo)
            .padding(top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            // CABEÇALHO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Criar Serviço",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = corTextoLabel
                )

                // Botão "X" amarelo
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(corDestaqueAmarelo, RoundedCornerShape(12.dp))
                        .clickable { voltarParaInicio() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "x",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // FORMULÁRIO
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
            ) {
                CampoFormularioDark(
                    titulo = "Título do Serviço",
                    conteudo = "Ex: Poda de Árvores e Paisagismo",
                    corBorda = corBordaCaixa
                )

                CampoFormularioDark(
                    titulo = "Categoria",
                    conteudo = "Ex: Limpeza e Manutenção Externa",
                    corBorda = corBordaCaixa
                )

                CampoFormularioDark(
                    titulo = "Valor / Orçamento",
                    conteudo = "R$ 50,00 / hora",
                    corBorda = corBordaCaixa
                )

                CampoFormularioDark(
                    titulo = "Descrição",
                    conteudo = "Descreva o serviço e os detalhes",
                    corBorda = corBordaCaixa
                )

                CampoFormularioDark(
                    titulo = "Requisitos Especiais",
                    conteudo = "Ex: ferramentas necessárias",
                    corBorda = corBordaCaixa
                )

                CampoFormularioDark(
                    titulo = "Localização",
                    conteudo = "Apenas cidade do serviço",
                    corBorda = corBordaCaixa
                )

                // Campo "Anexos" (Visual preenchido, sem borda fina)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "Anexos",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = corTextoLabel,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .background(Color(0xFF3B3026), RoundedCornerShape(8.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Espaço para adicionar anexos",
                            fontSize = 13.sp,
                            color = Color(0xFFAFA59B)
                        )
                    }
                }
            }
        }

        // BOTÃO INFERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = aoPublicar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = corDestaqueAmarelo),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Publicar Serviço",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// COMPOSABLE 2: Campos de formulário com altura e margens reduzidas
@Composable
fun CampoFormularioDark(
    titulo: String,
    conteudo: String,
    corBorda: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp) // Margem inferior reduzida
    ) {
        Text(
            text = titulo,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE5D5C5), // Bege claro
            modifier = Modifier.padding(bottom = 4.dp) // Distância menor entre label e input
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp) // Altura do input reduzida
                .border(1.dp, corBorda, RoundedCornerShape(4.dp)) // Borda sutil
                .background(Color.Transparent)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically // Centraliza o texto verticalmente
        ) {
            Text(
                text = conteudo,
                fontSize = 13.sp,
                color = Color(0xFFAFA59B) // Texto do placeholder (cinza/marrom claro)
            )
        }
    }
}