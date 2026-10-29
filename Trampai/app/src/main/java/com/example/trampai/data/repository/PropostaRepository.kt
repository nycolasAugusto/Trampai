package com.example.trampai.data.repository


import com.example.trampai.data.local.Proposta
import com.example.trampai.data.local.PropostaDAO
import kotlinx.coroutines.flow.Flow

class PropostaRepository(private val propostaDao: PropostaDAO) {

    fun getAllPropostas(): Flow<List<Proposta>> = propostaDao.buscarTodos()

    suspend fun inserir(proposta: Proposta) {
        propostaDao.inserir(proposta)
    }

    suspend fun atualizar(proposta: Proposta) {
        propostaDao.atualizar(proposta)
    }

    suspend fun deletar(proposta: Proposta) {
        propostaDao.deletar(proposta)
    }
}