package com.example.textrepeaterapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TextRepeaterTable")
data class TextRepeater(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Int = 0,

    @ColumnInfo(name = "RepeatedText")
    val repeatedText: String,

    @ColumnInfo(name = "RepeatCount")
    val repeatCount: Int,

    @ColumnInfo(name = "IsNewLine")
    val isNewLine: Boolean,

    @ColumnInfo(name = "Style")
    val style: Int
)