package com.example.textrepeaterapp.core.di

import com.example.textrepeaterapp.data.reposImpls.TextRepeaterRepoImpl
import com.example.textrepeaterapp.domain.repoInterfaces.TextRepeaterRepo
import com.example.textrepeaterapp.domain.useCases.TextRepeaterUseCase
import org.koin.dsl.module

val domainModule = module {
    single<TextRepeaterRepo> {
        TextRepeaterRepoImpl(textRepeaterDAO = get())
    }

    single<TextRepeaterUseCase> {
        TextRepeaterUseCase(textRepeaterRepo = get())
    }
}