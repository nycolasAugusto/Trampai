package com.example.trampai

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Hora atual do aparelho, ex: "14:32"
fun horaAtual(): String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

// As listas continuam reativas (mutableStateListOf), mas agora são
// preenchidas com o que está no BANCO. Toda alteração passa pelos DAOs.
class MeuViewModel : ViewModel() {

    val servicos = mutableStateListOf<Servico>()
    val propostas = mutableStateListOf<Proposta>()

    // =============== SERVIÇOS ===============

    // BUSCAR: chamada dentro de um LaunchedEffect(Unit) ao abrir a tela
    suspend fun carregarServicos(db: AppDatabase) {
        try {
            val lista = db.servicoDAO().buscarTodos()
            servicos.clear()
            servicos.addAll(lista)
        } catch (e: Exception) {
            Log.e("Trampai", "Erro ao buscar serviços", e)
        }
    }

    // DETALHE: busca um único serviço pelo id que veio na rota
    suspend fun buscarServicoPorId(db: AppDatabase, id: Int): Servico? {
        return try {
            db.servicoDAO().buscarPorId(id)
        } catch (e: Exception) {
            Log.e("Trampai", "Erro ao buscar serviço $id", e)
            null
        }
    }

    // INSERIR
    fun adicionarServico(db: AppDatabase, servico: Servico) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.servicoDAO().inserir(servico)
                carregarServicos(db)
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao inserir serviço", e)
            }
        }
    }

    // ATUALIZAR
    fun atualizarServico(db: AppDatabase, servico: Servico) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.servicoDAO().atualizar(servico)
                carregarServicos(db)
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao atualizar serviço", e)
            }
        }
    }

    // REMOVER
    fun removerServico(db: AppDatabase, servico: Servico) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.propostaDAO().deletarPorServico(servico.id)
                db.servicoDAO().deletar(servico)
                carregarServicos(db)
                carregarPropostas(db)
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao remover serviço", e)
            }
        }
    }

    // =============== PROPOSTAS ===============

    // BUSCAR
    suspend fun carregarPropostas(db: AppDatabase) {
        try {
            val lista = db.propostaDAO().buscarTodos()
            propostas.clear()
            propostas.addAll(lista)
        } catch (e: Exception) {
            Log.e("Trampai", "Erro ao buscar propostas", e)
        }
    }

    // INSERIR: grava hora, valor pedido e requerente na hora do envio
    fun adicionarProposta(db: AppDatabase, proposta: Proposta) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val servico = db.servicoDAO().buscarPorId(proposta.servicoId)
                db.propostaDAO().inserir(
                    proposta.copy(
                        hora = horaAtual(),
                        valorServico = servico?.valorHora ?: 0.0,
                        requerente = servico?.autor ?: ""
                    )
                )
                carregarPropostas(db)
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao inserir proposta", e)
            }
        }
    }

    // REMOVER
    fun removerProposta(db: AppDatabase, proposta: Proposta) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.propostaDAO().deletar(proposta)
                carregarPropostas(db)
            } catch (e: Exception) {
                Log.e("Trampai", "Erro ao remover proposta", e)
            }
        }
    }
}