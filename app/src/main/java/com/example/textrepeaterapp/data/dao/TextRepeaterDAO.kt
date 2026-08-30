package com.example.textrepeaterapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.textrepeaterapp.domain.models.TextRepeater

@Dao
interface TextRepeaterDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveText(textRepeater: TextRepeater): Long
}