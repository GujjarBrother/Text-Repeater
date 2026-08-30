package com.example.textrepeaterapp.core.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.example.textrepeaterapp.R

class TextCopyController(
    private val clipboardManager: ClipboardManager,
    private val context: Context
) {
    fun copyText(text: String) {
        clipboardManager.setPrimaryClip(
            ClipData.newPlainText("Copied Text", text)
        )
        Toast.makeText(context, context.getString(R.string.text_copied_successfully_text), Toast.LENGTH_LONG).show()
    }
}