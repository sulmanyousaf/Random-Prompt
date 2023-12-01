package com.terafort.assesmentproject.data.model

data class PromptList(val prompts: List<Prompt>) {
    data class Prompt(val title: String, val prompt: String)
}