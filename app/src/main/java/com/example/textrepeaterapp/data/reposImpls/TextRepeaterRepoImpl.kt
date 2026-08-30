package com.example.textrepeaterapp.data.reposImpls

import com.example.textrepeaterapp.data.dao.TextRepeaterDAO
import com.example.textrepeaterapp.domain.models.TextRepeater
import com.example.textrepeaterapp.domain.repoInterfaces.TextRepeaterRepo

class TextRepeaterRepoImpl(
    private val textRepeaterDAO: TextRepeaterDAO
) : TextRepeaterRepo {
    override suspend fun saveText(textRepeater: TextRepeater): Long {
        return textRepeaterDAO.saveText(textRepeater)
    }
}