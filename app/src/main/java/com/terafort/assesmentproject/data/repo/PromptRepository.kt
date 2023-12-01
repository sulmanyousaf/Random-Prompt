package com.terafort.assesmentproject.data.repo

import android.content.Context
import com.google.gson.Gson
import com.terafort.assesmentproject.data.model.PromptList
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class PromptRepository @Inject constructor(@ApplicationContext private val context: Context) {
    private val _prompts = MutableStateFlow<List<PromptList.Prompt>>(emptyList())
    val prompts = _prompts.asStateFlow()

//    fun getJson(context: Context){
//        val assetManager = context.assets
//        val inputStream = assetManager.open("prompts.json")
//        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
//        val stringBuilder = StringBuilder()
//        var line: String? = bufferedReader.readLine()
//        while (line != null) {
//            stringBuilder.append(line)
//            line = bufferedReader.readLine()
//        }
//        bufferedReader.close()
////        val jsonString = stringBuilder.toString()
//        loadPrompts(stringBuilder.toString())
//    }
//fun getJson(context: Context) {
fun getJson() {
    val assetManager = context.assets
    val inputStream = assetManager.open("prompts.json")
    val jsonString = inputStream.use { input ->
        val bufferedReader = BufferedReader(InputStreamReader(input))
        bufferedReader.use { reader ->
            val stringBuilder = StringBuilder()
            var line: String? = reader.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = reader.readLine()
            }
            stringBuilder.toString()
        }
    }
    loadPrompts(jsonString)
}


    fun loadPrompts(jsonString: String) {
//    fun loadPrompts(jsonString: String, applicationContext: Context) {
        val gson = Gson()
        val promptList = gson.fromJson(jsonString, PromptList::class.java)
        _prompts.value = promptList.prompts
    }
}