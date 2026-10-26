package com.example.trampai

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update

@Dao
interface ServicoDAO {

    @Insert
    suspend fun inserir(servico: Servico)

    // Mais novos primeiro (igual ao que a Home mostra hoje)
    @Query("SELECT * FROM servicos ORDER BY id DESC")
    suspend fun buscarTodos(): List<Servico>

    @Update
    suspend fun atualizar(servico: Servico)

    @Delete
    suspend fun deletar(servico: Servico)

    // Consulta filtrada: usada pela tela de Descrição (id vindo da rota)
    @Query("SELECT * FROM servicos WHERE id = :id")
    suspend fun buscarPorId(id: Int): Servico?
}