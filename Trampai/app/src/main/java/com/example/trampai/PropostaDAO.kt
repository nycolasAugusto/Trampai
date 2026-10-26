package com.example.trampai

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update

@Dao
interface PropostaDAO {

    @Insert
    suspend fun inserir(proposta: Proposta)

    @Query("SELECT * FROM propostas ORDER BY id DESC")
    suspend fun buscarTodos(): List<Proposta>

    @Update
    suspend fun atualizar(proposta: Proposta)

    @Delete
    suspend fun deletar(proposta: Proposta)

    @Query("DELETE FROM propostas WHERE servicoId = :servicoId")
    suspend fun deletarPorServico(servicoId: Int)
}