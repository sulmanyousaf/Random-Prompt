package com.terafort.assesmentproject.fragments.prompt

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.terafort.assesmentproject.data.model.PromptList
import com.terafort.assesmentproject.data.repo.PromptRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//class PromptViewModel(private val repository: PromptRepository) : ViewModel() {
@HiltViewModel
class PromptViewModel @Inject constructor(repository: PromptRepository) : ViewModel() {
//class PromptViewModel(private val application: Application) : AndroidViewModel(application) {
//class PromptViewModel(private val repository: PromptRepository,private val application: Application) : AndroidViewModel(application) {

//    private val repository: PromptRepository = PromptRepository()
    private val jsonString = """
        {
            "prompts": [
                {
                    "title": "Sunset Dreams",
                    "prompt": "Create an image inspired by the colors of a beautiful sunset."
                },
                {
                    "title": "Enchanted Forest",
                    "prompt": "Design a mystical forest scene with magical creatures."
                },
                {
                    "title": "Cityscape Adventures",
                    "prompt": "Illustrate a bustling cityscape filled with futuristic technology."
                },
                {
                    "title": "Underwater Odyssey",
                    "prompt": "Paint an underwater world teeming with marine life and coral reefs."
                }
            ]
        }
    """.trimIndent()

    init {
//        repository.loadPrompts(jsonString)
        repository.getJson()
//        repository.loadPrompts(jsonString,application.applicationContext)
    }

    val prompts: StateFlow<List<PromptList.Prompt>> = repository.prompts

}