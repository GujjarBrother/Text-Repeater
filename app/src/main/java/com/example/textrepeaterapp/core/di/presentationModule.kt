package com.example.textrepeaterapp.core.di

import com.example.textrepeaterapp.presentation.home.HomeScreenViewModel
import com.example.textrepeaterapp.presentation.textRepeater.TextRepeaterScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::TextRepeaterScreenViewModel)
}