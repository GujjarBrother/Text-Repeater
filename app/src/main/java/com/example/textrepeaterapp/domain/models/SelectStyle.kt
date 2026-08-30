package com.example.textrepeaterapp.domain.models

import androidx.compose.ui.text.font.FontFamily
import com.example.textrepeaterapp.R

data class SelectStyle(
    val text: Int = R.string.first_style_text,
    val emoji: Int = R.string.first_style_emoji,
    val fontName: FontFamily? = null,
    var isSelected: Boolean = false
)