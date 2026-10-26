package com.example.trampai

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase

@Database(entities = [Servico::class, Proposta::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun servicoDAO(): ServicoDAO
    abstract fun propostaDAO(): PropostaDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            val temInstance = INSTANCE

            if (temInstance != null) {
                return temInstance
            } else {

                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "trampai_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}