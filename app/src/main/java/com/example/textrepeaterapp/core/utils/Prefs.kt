package com.example.textrepeaterapp.core.utils

import android.content.Context
import androidx.core.content.edit

class TextRepeaterPrefs(val mContext: Context) {
    private val textRepeaterSharedPreferences = mContext.getSharedPreferences("TEXT_REPEATER_PREFS", Context.MODE_PRIVATE)

    var selectedStyle: Int
        get() = textRepeaterSharedPreferences.getInt("selectedStyle", 0)
        set(value) {
            textRepeaterSharedPreferences.edit {
                putInt("selectedStyle", value)
                apply()
            }
        }
}