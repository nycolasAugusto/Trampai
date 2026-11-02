package com.example.trampai.viewmodel.servicos


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trampai.data.repository.ServicoRepository

class ServicosViewModelFactory(
    private val repository: ServicoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServicosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ServicosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}