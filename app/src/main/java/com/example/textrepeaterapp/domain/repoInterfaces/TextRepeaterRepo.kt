package com.example.textrepeaterapp.domain.repoInterfaces

import com.example.textrepeaterapp.domain.models.TextRepeater

interface TextRepeaterRepo {
    suspend fun saveText(textRepeater: TextRepeater): Long
}