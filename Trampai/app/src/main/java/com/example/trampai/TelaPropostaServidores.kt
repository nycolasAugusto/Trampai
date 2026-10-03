package com.example.trampai

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trampai.ui.theme.TrampaiTheme

data class Profissional(
    val id: Int,
    val nome: String,
    val profissao: String,
    val avaliacao: String,
    val verificado: Boolean,
    val isOnline: Boolean,
    val corAvatar: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPropostasServidores(
    voltarParaInicio: () -> Unit = {},
    aoSelecionarProfissional: (Profissional) -> Unit = {}
) {
    val backgroundColor = Color(0xFFF8F9FD)
    val primaryColor = Color(0xFF6366F1)
    val textColorPrimary = Color(0xFF0F172A)

    var searchText by remember { mutableStateOf("") }
    var filtroSelecionado by remember { mutableStateOf("Todos") }

    val listaProfissionais = remember {
        listOf(
            Profissional(1, "Ana Lima", "Diarista", "4.9", verificado = true, isOnline = true, corAvatar = Color(0xFFF472B6)),
            Profissional(2, "Carlos M.", "Eletricista", "4.8", verificado = true, isOnline = true, corAvatar = Color(0xFF3B82F6)),
            Profissional(3, "Sofia R.", "Pintora", "4.7", verificado = false, isOnline = false, corAvatar = Color(0xFFEF4444)),
            Profissional(4, "Pedro H.", "Encanador", "4.9", verificado = true, isOnline = true, corAvatar = Color(0xFF10B981)),
            Profissional(5, "Rita C.", "Cozinheira", "5.0", verificado = true, isOnline = false, corAvatar = Color(0xFFF59E0B)),
            Profissional(6, "João V.", "Técnico TI", "4.6", verificado = false, isOnline = true, corAvatar = Color(0xFF6366F1)),
            Profissional(7, "Mariana S.", "Jardineira", "4.8", verificado = true, isOnline = false, corAvatar = Color(0xFF14B8A6)),
            Profissional(8, "Lucas P.", "Pintor", "4.7", verificado = true, isOnline = true, corAvatar = Color(0xFF8B5CF6)),
            Profissional(9, "Clara F.", "Cabeleireira", "4.9", verificado = true, isOnline = true, corAvatar = Color(0xFFEC4899))
        )
    }

    val profissionaisFiltrados = listaProfissionais.filter { prof ->
        val atendeBusca = prof.nome.contains(searchText, ignoreCase = true) ||
                prof.profissao.contains(searchText, ignoreCase = true)

        val atendeFiltro = when (filtroSelecionado) {
            "Online" -> prof.isOnline
            "Verificados" -> prof.verificado
            "Melhor avaliados" -> (prof.avaliacao.toDoubleOrNull() ?: 0.0) >= 4.8
            else -> true
        }

        atendeBusca && atendeFiltro
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 18.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // CABEÇALHO
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                onClick = voltarParaInicio,
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color(0xFF1E293B),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Propostas de Servidores",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColorPrimary
                )
                Text(
                    text = "${profissionaisFiltrados.size} profissionais disponíveis",
                    fontSize = 13.sp,
                    color = Color(0xFF94A3B8)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // BUSCA E FILTRO
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = {
                    Text(
                        text = "Buscar profissional...",
                        fontSize = 14.sp,
                        color = Color(0xFF94A3B8)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color(0xFF94A3B8),
                        modifier = Modifier.size(20.dp)
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = Color(0xFFE2E8F0),
                    focusedTextColor = textColorPrimary,
                    unfocusedTextColor = textColorPrimary
                )
            )

            // Botão de filtro roxo
            Surface(
                onClick = { /* Ação filtro */ },
                shape = RoundedCornerShape(16.dp),
                color = primaryColor,
                modifier = Modifier.size(52.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = "Filtro",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TAGS DE FILTRO
        val tagsFiltro = listOf("Todos", "Online", "Verificados", "Melhor avaliados")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(tagsFiltro) { tag ->
                val selecionado = tag == filtroSelecionado
                Surface(
                    onClick = { filtroSelecionado = tag },
                    shape = RoundedCornerShape(50),
                    color = if (selecionado) primaryColor else Color.White,
                    border = if (selecionado) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    modifier = Modifier.height(36.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = tag,
                            fontSize = 13.sp,
                            fontWeight = if (selecionado) FontWeight.Bold else FontWeight.Medium,
                            color = if (selecionado) Color.White else Color(0xFF64748B)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // GRID DE PROFISSIONAIS (3 Colunas)
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(profissionaisFiltrados) { prof ->
                CardProfissional(
                    profissional = prof,
                    onClick = { aoSelecionarProfissional(prof) }
                )
            }
        }
    }
}

@Composable
fun CardProfissional(
    profissional: Profissional,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Badge verificado
            if (profissional.verificado) {
                Box(
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Verificado",
                        tint = Color(0xFF818CF8),
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 4.dp)
            ) {
                // Avatar com indicador online
                Box(contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(profissional.corAvatar),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(13.dp)
                            .clip(CircleShape)
                            .background(if (profissional.isOnline) Color(0xFF10B981) else Color(0xFFCBD5E1))
                            .border(2.dp, Color.White, CircleShape)
                            .align(Alignment.BottomEnd)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = profissional.nome,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                    maxLines = 1
                )

                Text(
                    text = profissional.profissao,
                    fontSize = 10.sp,
                    color = Color(0xFF94A3B8),
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = profissional.avaliacao,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaPropostasServidores() {
    TrampaiTheme {
        TelaPropostasServidores()
    }
}