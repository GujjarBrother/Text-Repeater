package com.example.textrepeaterapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.textrepeaterapp.data.dao.TextRepeaterDAO
import com.example.textrepeaterapp.domain.models.TextRepeater

@Database(entities = [TextRepeater::class], version = 1)
abstract class TextRepeaterDatabase : RoomDatabase() {
    abstract fun textRepeaterDao(): TextRepeaterDAO
}