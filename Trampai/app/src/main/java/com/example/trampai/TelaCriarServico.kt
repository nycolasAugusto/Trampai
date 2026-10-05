package com.example.trampai

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val roxo = Color(0xFF5A4FCF)
private val textoEscuro = Color(0xFF0F172A)
private val textoSuave = Color(0xFF64748B)

@Composable
fun TelaCriarServico(voltarParaInicio: () -> Unit, aoPublicar: (Servico) -> Unit) {
    var titulo by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var requisitos by remember { mutableStateOf("") }
    var localizacao by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }

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
                    .clickable { voltarParaInicio() },
                contentAlignment = Alignment.Center
            ) {
                Text("x", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
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
                    Campo("✏️", "Título do Serviço", titulo, { titulo = it })
                    Campo("🏷️", "Categoria (ex: Limpeza)", categoria, { categoria = it })
                    Campo("💰", "Valor por hora (R$)", valor, { valor = it }, KeyboardType.Decimal)
                    Campo("📝", "Descrição", descricao, { descricao = it }, linhas = 3)
                    Campo("📋", "Requisitos Especiais", requisitos, { requisitos = it })
                    Campo("📍", "Localização (cidade)", localizacao, { localizacao = it })
                }
            }
        }

        // ERRO + BOTÃO
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            if (erro.isNotEmpty()) {
                Text(erro, color = Color(0xFFD32F2F), fontSize = 13.sp, modifier = Modifier.padding(bottom = 8.dp))
            }
            Button(
                onClick = {
                    val valorNumero = valor.replace(",", ".").toDoubleOrNull()
                    erro = when {
                        titulo.isBlank() -> "Informe o título do serviço."
                        categoria.isBlank() -> "Informe a categoria."
                        valorNumero == null || valorNumero <= 0 -> "Informe um valor válido, ex: 50 ou 50,50."
                        else -> ""
                    }
                    if (erro.isEmpty() && valorNumero != null) {
                        aoPublicar(
                            Servico(
                                id = 0, // o ViewModel define o id
                                titulo = titulo.trim(),
                                categoria = categoria.trim(),
                                valorHora = valorNumero,
                                descricao = descricao.trim(),
                                requisitos = requisitos.trim(),
                                localizacao = localizacao.trim().ifBlank { "Local não informado" },
                                autor = "Alex Souza"
                            )
                        )
                    }
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
    icone: String,
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
        leadingIcon = { Text(icone) },
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