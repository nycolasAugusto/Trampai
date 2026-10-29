package com.example.trampai.data.repository

import com.example.trampai.data.local.PropostaDAO
import com.example.trampai.data.local.Servico
import com.example.trampai.data.local.ServicoDAO
import kotlinx.coroutines.flow.Flow

class ServicoRepository(
    private val servicoDao: ServicoDAO,
    private val propostaDao: PropostaDAO
) {

    fun getAllServicos(): Flow<List<Servico>> = servicoDao.buscarTodos()

    suspend fun buscarPorId(id: Int): Servico? = servicoDao.buscarPorId(id)

    suspend fun inserir(servico: Servico) {
        servicoDao.inserir(servico)
    }

    suspend fun atualizar(servico: Servico) {
        servicoDao.atualizar(servico)
    }

    // As propostas do serviço saem junto, para não sobrar proposta sem serviço
    suspend fun deletar(servico: Servico) {
        propostaDao.deletarPorServico(servico.id)
        servicoDao.deletar(servico)
    }
}