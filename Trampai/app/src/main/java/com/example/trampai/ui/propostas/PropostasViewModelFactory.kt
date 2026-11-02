package com.example.trampai.ui.propostas


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trampai.data.repository.PropostaRepository

class PropostasViewModelFactory(
    private val repository: PropostaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PropostasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PropostasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}