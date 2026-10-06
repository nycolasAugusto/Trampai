package com.example.trampai

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaPerfil(
    voltarParaInicio: () -> Unit,
    verPropostas: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {

        // TOPO: título + setinha de voltar no canto direito
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Meu Perfil",
                color = Color(0xFF5A4FCF),
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { voltarParaInicio() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color(0xFF5A4FCF))
            }
        }

        // CARD PRINCIPAL: avatar + informações
        CardPerfilPrincipal(
            nome = "Alex Souza",
            email = "alex.souza@email.com",
            local = "Copacabana, RJ"
        )

        // TÍTULO DA SEÇÃO
        Text(
            text = "RESUMO",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )

        // GRADE 2x2 DE CARDS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CardInfo(
                modifier = Modifier.weight(1f),
                icone = Icons.Default.Work,
                titulo = "Serviços",
                valor = "12"
            )
            CardInfo(
                modifier = Modifier.weight(1f),
                icone = Icons.Default.Star,
                titulo = "Avaliação",
                valor = "4.9"
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CardInfo(
                modifier = Modifier.weight(1f),
                icone = Icons.Default.Favorite,
                titulo = "Favoritos",
                valor = "8"
            )
            CardInfo(
                modifier = Modifier.weight(1f),
                icone = Icons.Default.Email,
                titulo = "Mensagens",
                valor = "3"
            )
        }

        // CARD: Propostas de Servidores (abre a tela de propostas)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .clickable { verPropostas() }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Propostas de Servidores",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Veja quem quer atender seus serviços",
                    fontSize = 13.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color(0xFF5A4FCF))
        }
    }
}

// COMPOSABLE: Card com avatar à esquerda e textos à direita
@Composable
fun CardPerfilPrincipal(nome: String, email: String, local: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar simulado (círculo com a inicial)
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFF5A4FCF), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = nome.first().toString(),
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = nome,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = email,
                fontSize = 13.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 4.dp)
            )
            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                Text(text = " $local", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

// COMPOSABLE: Card pequeno de informação
@Composable
fun CardInfo(modifier: Modifier = Modifier, icone: ImageVector, titulo: String, valor: String) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .height(110.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(icone, contentDescription = titulo, tint = Color(0xFF5A4FCF), modifier = Modifier.size(24.dp))

        Column {
            Text(
                text = valor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5A4FCF)
            )
            Text(
                text = titulo,
                fontSize = 13.sp,
                color = Color.DarkGray
            )
        }
    }
}