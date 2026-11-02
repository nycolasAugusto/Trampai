package com.example.trampai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trampai.data.local.Servico
import com.example.trampai.data.local.valorFormatado
import com.example.trampai.viewmodel.propostas.PropostasUiState
import com.example.trampai.viewmodel.propostas.PropostasViewModel
import com.example.trampai.viewmodel.servicos.ServicosUiState
import com.example.trampai.viewmodel.servicos.ServicosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDescricaoServico(
    servicoId: Int,
    servicosViewModel: ServicosViewModel,
    propostasViewModel: PropostasViewModel,
    voltarParaInicio: () -> Unit,
    irParaPropostas: () -> Unit
) {
    val backgroundColor = Color(0xFFF8F9FD)
    val primaryColor = Color(0xFF6366F1)
    val textColorPrimary = Color(0xFF0F172A)
    val textColorSecondary = Color(0xFF64748B)

    val servicosState by servicosViewModel.uiState.collectAsStateWithLifecycle()
    val propostasState by propostasViewModel.uiState.collectAsStateWithLifecycle()

    // Só controlam se cada diálogo está aberto (estado visual, não de dados)
    var mostrarFormulario by remember { mutableStateOf(false) }
    var mostrarEdicao by remember { mutableStateOf(false) }

    // DETALHE LENDO DO BANCO: o ViewModel busca o serviço pelo id que veio na rota
    LaunchedEffect(servicoId) {
        servicosViewModel.carregarServico(servicoId)
    }

    // Enquanto o serviço não chega do banco (ou se for de outro id), não desenha nada
    val servico = servicosState.servicoSelecionado?.takeIf { it.id == servicoId } ?: return

    // Combina as duas listas: conta as propostas que pertencem a este serviço
    val qtdPropostas = propostasState.listaDePropostas.count { it.servicoId == servico.id }

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

            Spacer(modifier = Modifier.weight(1f))

            // EDITAR: joga o serviço no formulário do ViewModel e abre o diálogo
            Surface(
                onClick = {
                    servicosViewModel.onEdit(servico)
                    mostrarEdicao = true
                },
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar serviço",
                        tint = Color(0xFF1E293B),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
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
                                text = servico.categoria,
                                color = primaryColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }

                        Text(
                            text = servico.valorFormatado,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = primaryColor
                        )
                    }

                    Text(
                        text = servico.titulo,
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
                            text = servico.localizacao,
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
                                text = servico.autor,
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
                            text = "Cliente cadastrado",
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
                        text = servico.descricao.ifBlank { "Sem descrição." },
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

                    Text(
                        text = servico.requisitos.ifBlank { "Sem requisitos especiais." },
                        fontSize = 13.sp,
                        color = textColorSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        // RESUMO (combina as duas listas) + BOTÃO INFERIOR
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Email, contentDescription = null, tint = textColorSecondary, modifier = Modifier.size(16.dp))
            Text(
                text = " $qtdPropostas proposta(s) enviada(s) para este serviço",
                fontSize = 13.sp,
                color = textColorSecondary
            )
        }

        Button(
            onClick = { mostrarFormulario = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
        ) {
            Text(
                text = "Enviar Proposta",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

    if (mostrarEdicao) {
        DialogEditarServico(
            uiState = servicosState,
            viewModel = servicosViewModel,
            aoFechar = { mostrarEdicao = false }
        )
    }

    if (mostrarFormulario) {
        DialogProposta(
            servico = servico,
            uiState = propostasState,
            viewModel = propostasViewModel,
            aoFechar = { mostrarFormulario = false },
            aoEnviado = {
                mostrarFormulario = false
                irParaPropostas()
            }
        )
    }
}

// Formulário da proposta: os campos moram no PropostasUiState
@Composable
private fun DialogProposta(
    servico: Servico,
    uiState: PropostasUiState,
    viewModel: PropostasViewModel,
    aoFechar: () -> Unit,
    aoEnviado: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            viewModel.onCancelar()
            aoFechar()
        },
        title = { Text("Nova proposta") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(servico.titulo, fontWeight = FontWeight.Bold)

                OutlinedTextField(
                    value = uiState.valor,
                    onValueChange = viewModel::onValorChange,
                    label = { Text("Seu valor por hora (R$)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.mensagem,
                    onValueChange = viewModel::onMensagemChange,
                    label = { Text("Mensagem") },
                    minLines = 3
                )

                if (uiState.erro.isNotEmpty()) {
                    Text(uiState.erro, color = Color(0xFFD32F2F), fontSize = 13.sp)
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                // onEnviar devolve true quando o formulário é válido
                if (viewModel.onEnviar(servico)) aoEnviado()
            }) { Text("Enviar") }
        },
        dismissButton = {
            TextButton(onClick = {
                viewModel.onCancelar()
                aoFechar()
            }) { Text("Cancelar") }
        }
    )
}

// Formulário de edição: usa os mesmos campos do ServicosUiState
@Composable
private fun DialogEditarServico(
    uiState: ServicosUiState,
    viewModel: ServicosViewModel,
    aoFechar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            viewModel.onCancelar()
            aoFechar()
        },
        title = { Text("Editar serviço") },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CampoEdicao("Título", uiState.titulo, viewModel::onTituloChange)
                CampoEdicao("Categoria", uiState.categoria, viewModel::onCategoriaChange)
                CampoEdicao("Valor por hora (R$)", uiState.valor, viewModel::onValorChange, KeyboardType.Decimal)
                CampoEdicao("Descrição", uiState.descricao, viewModel::onDescricaoChange, linhas = 3)
                CampoEdicao("Requisitos", uiState.requisitos, viewModel::onRequisitosChange)
                CampoEdicao("Localização", uiState.localizacao, viewModel::onLocalizacaoChange)

                if (uiState.erro.isNotEmpty()) {
                    Text(uiState.erro, color = Color(0xFFD32F2F), fontSize = 13.sp)
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (viewModel.onSave()) aoFechar()
            }) { Text("Salvar") }
        },
        dismissButton = {
            TextButton(onClick = {
                viewModel.onCancelar()
                aoFechar()
            }) { Text("Cancelar") }
        }
    )
}

@Composable
private fun CampoEdicao(
    nome: String,
    valor: String,
    aoMudar: (String) -> Unit,
    teclado: KeyboardType = KeyboardType.Text,
    linhas: Int = 1
) {
    OutlinedTextField(
        value = valor,
        onValueChange = aoMudar,
        label = { Text(nome) },
        singleLine = linhas == 1,
        minLines = linhas,
        keyboardOptions = KeyboardOptions(keyboardType = teclado)
    )
}