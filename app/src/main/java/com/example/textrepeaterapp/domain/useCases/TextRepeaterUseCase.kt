package com.example.textrepeaterapp.domain.useCases

import com.example.textrepeaterapp.domain.models.TextRepeater
import com.example.textrepeaterapp.domain.repoInterfaces.TextRepeaterRepo

class TextRepeaterUseCase(
    private val textRepeaterRepo: TextRepeaterRepo
) {
    suspend fun saveText(textRepeater: TextRepeater): Long {
        return textRepeaterRepo.saveText(textRepeater)
    }
}