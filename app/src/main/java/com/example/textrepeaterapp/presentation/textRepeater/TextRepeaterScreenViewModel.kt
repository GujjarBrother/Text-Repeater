package com.example.textrepeaterapp.presentation.textRepeater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textrepeaterapp.R
import com.example.textrepeaterapp.core.utils.righteousRegular
import com.example.textrepeaterapp.core.utils.robotoMedium
import com.example.textrepeaterapp.core.utils.rochesterRegular
import com.example.textrepeaterapp.core.utils.rokkittMedium
import com.example.textrepeaterapp.core.utils.romanescoRegular
import com.example.textrepeaterapp.core.utils.rougeScriptRegular
import com.example.textrepeaterapp.domain.models.SelectStyle
import com.example.textrepeaterapp.domain.models.TextRepeater
import com.example.textrepeaterapp.domain.useCases.TextRepeaterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TextRepeaterScreenState(
    val repeatableText: String? = null,
    val repeatCount: String? = null,
    val isRepeatableTextEmpty: Boolean = false,
    val isRepeatCountTextEmpty: Boolean = false,
    val isShowSelectStyleBottomSheet: Boolean = false,
    val isShowRepeatedText: Boolean = false,
    val isShowRepeatableTextLimitExceedsText: Boolean = false,
    val isShowRepeatCountLimitExceedsText: Boolean = false,
    val selectStyleList: List<SelectStyle> = emptyList(),
    val selectStyle: SelectStyle? = null,
    val isNewLineSwitchChecked: Boolean = false
)

class TextRepeaterScreenViewModel(
    val textRepeaterUseCase: TextRepeaterUseCase
) : ViewModel() {
    private var _state = MutableStateFlow(value = TextRepeaterScreenState())
    val state = _state.asStateFlow()

    init {
        resetStyle()
    }

    fun updateRepeatableText(repeatableText: String? = null) {
        _state.update {
            it.copy(repeatableText = repeatableText)
        }
    }

    fun updateRepeatCount(repeatCount: String? = null) {
        _state.update {
            it.copy(repeatCount = repeatCount)
        }
    }

    fun updateIsShowSelectStyleBottomSheet(isShowSelectStyleBottomSheet: Boolean) {
        _state.update {
            it.copy(isShowSelectStyleBottomSheet = isShowSelectStyleBottomSheet)
        }
    }

    fun updateStyleList(selectStyle: SelectStyle) {
        _state.update { it ->
            it.copy(
                selectStyleList = it.selectStyleList.map {
                    it.copy(isSelected = selectStyle == it)
                }, selectStyle = selectStyle
            )
        }
    }

    fun resetStyle() {
        _state.update {
            val stylesList = listOf(
                SelectStyle(
                    text = R.string.first_style_text,
                    emoji = R.string.first_style_emoji,
                    fontName = robotoMedium
                ), SelectStyle(
                    text = R.string.second_style_text,
                    emoji = R.string.second_style_emoji,
                    fontName = righteousRegular
                ), SelectStyle(
                    text = R.string.third_style_text,
                    emoji = R.string.third_style_emoji,
                    fontName = rochesterRegular
                ), SelectStyle(
                    text = R.string.fourth_style_text,
                    emoji = R.string.fourth_style_emoji,
                    fontName = rokkittMedium
                ), SelectStyle(
                    text = R.string.fifth_style_text,
                    emoji = R.string.fifth_style_emoji,
                    fontName = rougeScriptRegular
                ), SelectStyle(
                    text = R.string.sixth_style_text,
                    emoji = R.string.sixth_style_emoji,
                    fontName = romanescoRegular
                )
            )
            stylesList[0].isSelected = true
            it.copy(
                selectStyleList = stylesList, selectStyle = stylesList[0]
            )
        }
    }

    fun updateIsRepeatableTextEmpty(isRepeatableTextEmpty: Boolean) {
        _state.update {
            it.copy(isRepeatableTextEmpty = isRepeatableTextEmpty)
        }
    }

    fun updateIsRepeatCountTextEmpty(isRepeatCountTextEmpty: Boolean) {
        _state.update {
            it.copy(isRepeatCountTextEmpty = isRepeatCountTextEmpty)
        }
    }

    fun updateIsShowRepeatedText(isShowRepeatedText: Boolean) {
        _state.update {
            it.copy(isShowRepeatedText = isShowRepeatedText)
        }
    }

    fun updateIsShowRepeatableTextLimitExceedsText(isShow: Boolean) {
        _state.update {
            it.copy(isShowRepeatableTextLimitExceedsText = isShow)
        }
    }

    fun updateIsShowRepeatCountLimitExceedsText(isShow: Boolean) {
        _state.update {
            it.copy(isShowRepeatCountLimitExceedsText = isShow)
        }
    }

    fun updateIsNewLineSwitchChecked(isChecked: Boolean) {
        _state.update {
            it.copy(isNewLineSwitchChecked = isChecked)
        }
    }

    fun getStyledText(): String {
        return buildString {
            repeat(_state.value.repeatCount?.toIntOrNull() ?: 1) {
                append(getActualText())
                if (((it + 1) % if (_state.value.isNewLineSwitchChecked) 1 else 2) == 0) {
                    append("\n")
                } else {
                    append(" ")
                }
            }
        }
    }

    private fun getActualText() = when (_state.value.selectStyle?.fontName) {
        robotoMedium -> "${_state.value.repeatableText}😘💕"
        righteousRegular -> "🌟${_state.value.repeatableText}🌟"
        rochesterRegular -> "✨😊${_state.value.repeatableText}✨😊"
        rokkittMedium -> "💪💖${_state.value.repeatableText}💪💖"
        rougeScriptRegular -> "🌈${_state.value.repeatableText}🌈"
        romanescoRegular -> "☀️💛${_state.value.repeatableText}☀️💛"
        else -> ""
    }

    suspend fun saveText(): Boolean {
        val textRepeater = TextRepeater(
            repeatedText = _state.value.repeatableText ?: "",
            repeatCount = _state.value.repeatCount?.toIntOrNull() ?: 1,
            isNewLine = _state.value.isNewLineSwitchChecked,
            style = when (_state.value.selectStyle?.fontName) {
                robotoMedium -> 0
                righteousRegular -> 1
                rochesterRegular -> 2
                rokkittMedium -> 3
                rougeScriptRegular -> 4
                romanescoRegular -> 5
                else -> 0
            }
        )
        return viewModelScope.async(Dispatchers.IO) {
            val savedRowID = textRepeaterUseCase.saveText(textRepeater = textRepeater)
            savedRowID >= 0
        }.await()
    }
}