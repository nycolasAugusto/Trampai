    package com.example.trampai

    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.automirrored.filled.ArrowBack
    import androidx.compose.material.icons.filled.CheckCircle
    import androidx.compose.material.icons.filled.LocationOn
    import androidx.compose.material.icons.filled.Person
    import androidx.compose.material.icons.filled.Star
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TelaDescricaoServico(
        voltarParaInicio: () -> Unit = {},
        verPropostas: () -> Unit = {}
    ) {
        val backgroundColor = Color(0xFFF8F9FD)
        val primaryColor = Color(0xFF6366F1)
        val textColorPrimary = Color(0xFF0F172A)
        val textColorSecondary = Color(0xFF64748B)

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

                Text(
                    text = "Descrição do Serviço",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColorPrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // CONTEÚDO COM ROLAGEM
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // CARD PRINCIPAL DO SERVIÇO
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = primaryColor.copy(alpha = 0.1f)
                            ) {
                                Text(
                                    text = "Limpeza Residencial",
                                    color = primaryColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                )
                            }

                            Text(
                                text = "R$ 120/hr",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = primaryColor
                            )
                        }

                        Text(
                            text = "Serviço de Limpeza Profunda Completa",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColorPrimary
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = textColorSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Copacabana, RJ (1,2 km de distância)",
                                fontSize = 13.sp,
                                color = textColorSecondary
                            )
                        }
                    }
                }

                // SEÇÃO DO CLIENTE / SOLICITANTE
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF472B6)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Clara Mendes",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = textColorPrimary
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Verificado",
                                    tint = primaryColor,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Text(
                                text = "Cliente cadastrada • 12 serviços solicitados",
                                fontSize = 12.sp,
                                color = textColorSecondary
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(0xFFF59E0B),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "4.9",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColorPrimary
                            )
                        }
                    }
                }

                // DETALHES E DESCRIÇÃO
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Descrição Detalhada",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColorPrimary
                        )

                        Text(
                            text = "Procura-se profissional para realizar limpeza pesada e organização completa de apartamento de 3 quartos. O serviço inclui aspiração, lavagem de pisos, higienização dos banheiros, limpeza da cozinha e vidros.",
                            fontSize = 14.sp,
                            color = Color(0xFF475569),
                            lineHeight = 20.sp
                        )

                        HorizontalDivider(color = Color(0xFFF1F5F9))

                        Text(
                            text = "Requisitos & Observações",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColorPrimary
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("• Trazer produtos e equipamentos básicos de limpeza", fontSize = 13.sp, color = textColorSecondary)
                            Text("• Pontualidade e experiência prévia comprovada", fontSize = 13.sp, color = textColorSecondary)
                            Text("• Estimativa de duração: 4 a 6 horas", fontSize = 13.sp, color = textColorSecondary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            // BOTÃO INFERIOR
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                color = Color.Transparent
            ) {
                Button(
                    onClick = verPropostas,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                ) {
                    Text(
                        text = "Ver Propostas dos Servidores",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewTelaDescricaoServico() {
        TrampaiTheme {
            TelaDescricaoServico()
        }
    }
