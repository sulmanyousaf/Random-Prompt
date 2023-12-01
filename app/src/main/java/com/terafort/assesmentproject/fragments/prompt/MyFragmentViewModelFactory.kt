package com.terafort.assesmentproject.fragments.prompt

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terafort.assesmentproject.data.repo.PromptRepository

class PromptFragmentViewModelFactory(
    private val repository: PromptRepository
) :
    ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(PromptViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return PromptViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
}