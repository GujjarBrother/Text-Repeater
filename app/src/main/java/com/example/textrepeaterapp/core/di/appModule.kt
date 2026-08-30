package com.example.textrepeaterapp.core.di

import android.content.ClipboardManager
import android.content.Context
import androidx.room.Room
import com.example.textrepeaterapp.core.utils.TextCopyController
import com.example.textrepeaterapp.core.utils.TextRepeaterPrefs
import com.example.textrepeaterapp.data.dao.TextRepeaterDAO
import com.example.textrepeaterapp.data.db.TextRepeaterDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<ClipboardManager> {
        androidContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    single {
        TextCopyController(clipboardManager = get(), context = get())
    }

    single {
        TextRepeaterPrefs(mContext = get())
    }

    single<TextRepeaterDatabase> {
        Room.databaseBuilder(get(), TextRepeaterDatabase::class.java, "TextRepeaterDatabase").build()
    }

    single<TextRepeaterDAO> {
        get<TextRepeaterDatabase>().textRepeaterDao()
    }
}