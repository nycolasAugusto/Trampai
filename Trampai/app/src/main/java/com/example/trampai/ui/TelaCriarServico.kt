package com.example.trampai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trampai.ui.servicos.ServicosViewModel

private val roxo = Color(0xFF5A4FCF)
private val textoEscuro = Color(0xFF0F172A)
private val textoSuave = Color(0xFF64748B)

@Composable
fun TelaCriarServico(
    viewModel: ServicosViewModel,
    voltarParaInicio: () -> Unit,
    aoPublicar: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Se sobrou uma edição aberta em outra tela, começa com o formulário limpo
    LaunchedEffect(Unit) {
        if (uiState.servicoEmEdicao != null) viewModel.onCancelar()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .imePadding()
            .padding(vertical = 16.dp)
    ) {
        // CABEÇALHO
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Criar Serviço", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = roxo)
                Text("Conte o que você precisa e receba propostas", fontSize = 13.sp, color = textoSuave)
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(roxo, RoundedCornerShape(12.dp))
                    .clickable {
                        viewModel.onCancelar()
                        voltarParaInicio()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Close, contentDescription = "Fechar", tint = Color.White)
            }
        }

        // FORMULÁRIO EM UM CARD
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Campo(Icons.Default.Edit, "Título do Serviço", uiState.titulo, viewModel::onTituloChange)
                    Campo(Icons.Default.Category, "Categoria (ex: Limpeza)", uiState.categoria, viewModel::onCategoriaChange)
                    Campo(Icons.Default.AttachMoney, "Valor por hora (R$)", uiState.valor, viewModel::onValorChange, KeyboardType.Decimal)
                    Campo(Icons.Default.Description, "Descrição", uiState.descricao, viewModel::onDescricaoChange, linhas = 3)
                    Campo(Icons.Default.Build, "Requisitos Especiais", uiState.requisitos, viewModel::onRequisitosChange)
                    Campo(Icons.Default.LocationOn, "Localização (cidade)", uiState.localizacao, viewModel::onLocalizacaoChange)
                }
            }
        }

        // ERRO + BOTÃO
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            if (uiState.erro.isNotEmpty()) {
                Text(uiState.erro, color = Color(0xFFD32F2F), fontSize = 13.sp, modifier = Modifier.padding(bottom = 8.dp))
            }
            Button(
                onClick = {
                    // onSave devolve true quando o formulário é válido
                    if (viewModel.onSave()) aoPublicar()
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = roxo),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Publicar Serviço", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun Campo(
    icone: ImageVector,
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
        leadingIcon = { Icon(icone, contentDescription = null, tint = roxo) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = linhas == 1,
        minLines = linhas,
        keyboardOptions = KeyboardOptions(keyboardType = teclado),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF8F9FD),
            unfocusedContainerColor = Color(0xFFF8F9FD),
            focusedBorderColor = roxo,
            unfocusedBorderColor = Color(0xFFD5D8E0),
            focusedLabelColor = roxo,
            unfocusedLabelColor = textoSuave,
            cursorColor = roxo,
            focusedTextColor = textoEscuro,
            unfocusedTextColor = textoEscuro
        )
    )
}