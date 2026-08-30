package com.example.textrepeaterapp.domain.models

data class NavigationDrawer(
    val image: Int = 0,
    val name: Int = 0,
    val isShowArrow: Boolean = true,
    val isShowSelectedLanguage: Boolean = false,
    val selectedLanguage: Int = 0,
    val isShowVersionCode: Boolean = false
)