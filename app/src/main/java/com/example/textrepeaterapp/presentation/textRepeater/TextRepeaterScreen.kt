package com.example.textrepeaterapp.presentation.textRepeater

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.textrepeaterapp.R
import com.example.textrepeaterapp.core.utils.Color000000
import com.example.textrepeaterapp.core.utils.Color1B75BB
import com.example.textrepeaterapp.core.utils.Color2FB042
import com.example.textrepeaterapp.core.utils.Color8D8D8D
import com.example.textrepeaterapp.core.utils.ColorB71C1C
import com.example.textrepeaterapp.core.utils.ColorCCCCCC
import com.example.textrepeaterapp.core.utils.ColorF3F2F7
import com.example.textrepeaterapp.core.utils.ColorF5F5F5
import com.example.textrepeaterapp.core.utils.ColorF7F7F7
import com.example.textrepeaterapp.core.utils.ColorFFFFFF
import com.example.textrepeaterapp.core.utils.CustomSwitch
import com.example.textrepeaterapp.core.utils.HorizontalSpacer
import com.example.textrepeaterapp.core.utils.MyCustomButton
import com.example.textrepeaterapp.core.utils.SelectStyleBottomSheet
import com.example.textrepeaterapp.core.utils.TextCopyController
import com.example.textrepeaterapp.core.utils.TextRepeaterScreen
import com.example.textrepeaterapp.core.utils.TextRepeaterToolbar
import com.example.textrepeaterapp.core.utils.VerticalSpacer
import com.example.textrepeaterapp.core.utils.righteousRegular
import com.example.textrepeaterapp.core.utils.robotoMedium
import com.example.textrepeaterapp.core.utils.robotoRegular
import com.example.textrepeaterapp.core.utils.rochesterRegular
import com.example.textrepeaterapp.core.utils.rokkittMedium
import com.example.textrepeaterapp.core.utils.romanescoRegular
import com.example.textrepeaterapp.core.utils.rougeScriptRegular
import com.example.textrepeaterapp.core.utils.sfCompactRoundedMedium
import com.example.textrepeaterapp.core.utils.share
import com.example.textrepeaterapp.domain.models.SelectStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun TextRepeaterScreen(
    textRepeaterScreenViewModel: TextRepeaterScreenViewModel = koinViewModel(),
    textCopyController: TextCopyController = koinInject(),
    onBackClickCallBack: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val state = textRepeaterScreenViewModel.state.collectAsState().value

    LaunchedEffect(key1 = state.isShowRepeatableTextLimitExceedsText) {
        if (state.isShowRepeatableTextLimitExceedsText) {
            delay(timeMillis = 5000)
            textRepeaterScreenViewModel.updateIsShowRepeatableTextLimitExceedsText(isShow = false)
        }
    }

    LaunchedEffect(key1 = state.isShowRepeatCountLimitExceedsText) {
        if (state.isShowRepeatCountLimitExceedsText) {
            delay(timeMillis = 5000)
            textRepeaterScreenViewModel.updateIsShowRepeatCountLimitExceedsText(isShow = false)
        }
    }

    BackHandler {
        onBackClickCallBack?.invoke()
    }

    Scaffold(
        topBar = {
            TextRepeaterToolbar(
                title = stringResource(id = R.string.text_repeater_text),
                backArrow = R.drawable.back_arrow_image,
                backIconClickCallback = {
                    onBackClickCallBack?.invoke()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = ColorFFFFFF)
                .verticalScroll(state = rememberScrollState())
                .padding(horizontal = 13.sdp, vertical = 18.sdp)
        ) {
            Text(
                text = stringResource(id = R.string.type_text_text),
                fontSize = 12.ssp,
                fontFamily = sfCompactRoundedMedium,
                fontWeight = FontWeight.Medium,
                color = if (state.isRepeatableTextEmpty) ColorB71C1C else Color000000
            )
            VerticalSpacer()
            Box(
                modifier = Modifier
                    .border(
                        width = 1.sdp,
                        color = if (state.isRepeatableTextEmpty) ColorB71C1C else ColorCCCCCC,
                        shape = RoundedCornerShape(size = 10.sdp)
                    )
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.repeatableText ?: "",
                    onValueChange = {
                        if (it.isEmpty())
                            textRepeaterScreenViewModel.updateRepeatableText(repeatableText = null)
                        else {
                            textRepeaterScreenViewModel.updateIsRepeatableTextEmpty(
                                isRepeatableTextEmpty = false
                            )
                            textRepeaterScreenViewModel.updateRepeatableText(repeatableText = it)
                        }
                    },
                    textStyle = TextStyle(
                        color = Color000000,
                        fontSize = 12.ssp,
                        fontFamily = robotoMedium,
                        fontWeight = FontWeight.Medium
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.type_text_here_text),
                            fontSize = 12.ssp,
                            fontFamily = robotoRegular,
                            fontWeight = FontWeight.Normal,
                            color = if (state.isRepeatableTextEmpty) ColorB71C1C else Color8D8D8D
                        )
                    },
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.cross_image),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = if (state.isRepeatableTextEmpty) ColorB71C1C else Color000000),
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .background(color = ColorF5F5F5)
                                .clickable {
                                    textRepeaterScreenViewModel.updateRepeatableText(repeatableText = null)
                                }
                                .padding(all = 8.sdp)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(focusDirection = FocusDirection.Next)
                        }
                    ),
                    shape = RoundedCornerShape(size = 10.sdp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        cursorColor = if (state.isRepeatableTextEmpty) ColorB71C1C else Color2FB042,
                        unfocusedContainerColor = ColorFFFFFF,
                        focusedContainerColor = ColorFFFFFF
                    )
                )
            }
            VerticalSpacer(verticalSpace = 5)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AnimatedVisibility(
                    visible = state.isShowRepeatableTextLimitExceedsText,
                    enter = slideInHorizontally { -it } + fadeIn(),
                    exit = slideOutHorizontally { -it } + fadeOut()
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 5.sdp)
                            .weight(weight = 1F),
                        textAlign = TextAlign.End,
                        text = stringResource(id = R.string.limit_exceeds_text),
                        fontSize = 11.ssp,
                        fontFamily = sfCompactRoundedMedium,
                        fontWeight = FontWeight.Medium,
                        color = ColorB71C1C
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.sdp),
                    textAlign = TextAlign.End,
                    text = "${state.repeatableText?.length ?: "00"}/200",
                    fontSize = 11.ssp,
                    fontFamily = sfCompactRoundedMedium,
                    fontWeight = FontWeight.Medium,
                    color = if ((state.repeatableText?.length
                            ?: 0) >= 201
                    ) ColorB71C1C else Color8D8D8D
                )
            }
            VerticalSpacer(verticalSpace = 20)
            Text(
                text = stringResource(id = R.string.repeat_count_text),
                fontSize = 12.ssp,
                fontFamily = sfCompactRoundedMedium,
                fontWeight = FontWeight.Medium,
                color = if (state.isRepeatCountTextEmpty) ColorB71C1C else Color000000
            )
            VerticalSpacer()
            Box(
                modifier = Modifier
                    .border(
                        width = 1.sdp,
                        color = if (state.isRepeatCountTextEmpty) ColorB71C1C else ColorCCCCCC,
                        shape = RoundedCornerShape(size = 10.sdp)
                    )
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.repeatCount ?: "",
                    onValueChange = { repeatCount ->
                        textRepeaterScreenViewModel.updateIsRepeatCountTextEmpty(
                            isRepeatCountTextEmpty = false
                        )
                        if (repeatCount.all { it.isDigit() })
                            textRepeaterScreenViewModel.updateRepeatCount(repeatCount = repeatCount)
                    },
                    textStyle = TextStyle(
                        color = Color000000,
                        fontSize = 12.ssp,
                        fontFamily = robotoMedium,
                        fontWeight = FontWeight.Medium
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enter_repeat_count_here_text),
                            fontSize = 12.ssp,
                            fontFamily = robotoRegular,
                            fontWeight = FontWeight.Normal,
                            color = if (state.isRepeatCountTextEmpty) ColorB71C1C else Color8D8D8D
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Go
                    ),
                    keyboardActions = KeyboardActions(
                        onGo = {
                            softwareKeyboardController?.hide()
                            scope.launch {
                                delay(timeMillis = 30)
                                focusManager.clearFocus()
                            }
                        }
                    ),
                    shape = RoundedCornerShape(size = 10.sdp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        cursorColor = if (state.isRepeatCountTextEmpty) ColorB71C1C else Color2FB042,
                        unfocusedContainerColor = ColorFFFFFF,
                        focusedContainerColor = ColorFFFFFF
                    )
                )
            }
            VerticalSpacer(verticalSpace = 5)
            AnimatedVisibility(
                visible = state.isShowRepeatCountLimitExceedsText,
                enter = slideInHorizontally { -it } + fadeIn(),
                exit = slideOutHorizontally { -it } + fadeOut()
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 5.sdp),
                    textAlign = TextAlign.End,
                    text = stringResource(id = R.string.max_limit_is_2000_text),
                    fontSize = 11.ssp,
                    fontFamily = sfCompactRoundedMedium,
                    fontWeight = FontWeight.Medium,
                    color = ColorB71C1C
                )
            }
            VerticalSpacer(verticalSpace = 25)
            MyCustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp),
                buttonImage = R.drawable.repeat_text_image,
                buttonText = stringResource(id = R.string.repeat_text_text),
                buttonBackgroundColor = Color2FB042,
                buttonTextFont = sfCompactRoundedMedium,
                buttonClickCallback = {
                    textRepeaterScreenViewModel.apply {
                        if (state.repeatableText.isNullOrEmpty()) {
                            updateIsRepeatableTextEmpty(isRepeatableTextEmpty = true)
                            updateIsRepeatCountTextEmpty(isRepeatCountTextEmpty = false)
                        } else if (state.repeatableText.length >= 201) {
                            updateIsRepeatCountTextEmpty(isRepeatCountTextEmpty = false)
                            updateIsShowRepeatableTextLimitExceedsText(isShow = true)
                        } else if (state.repeatCount.isNullOrEmpty()) {
                            updateIsRepeatCountTextEmpty(isRepeatCountTextEmpty = true)
                            updateIsRepeatableTextEmpty(isRepeatableTextEmpty = false)
                        } else if ((state.repeatCount.toIntOrNull() ?: 2001) > 2000)
                            updateIsShowRepeatCountLimitExceedsText(isShow = true)
                        else
                            updateIsShowRepeatedText(isShowRepeatedText = true)
                    }
                }
            )

            if (state.isShowSelectStyleBottomSheet) {
                SelectStyleBottomSheet(
                    selectStyleList = state.selectStyleList,
                    onDismissCallback = {
                        textRepeaterScreenViewModel.updateIsShowSelectStyleBottomSheet(
                            isShowSelectStyleBottomSheet = false
                        )
                    },
                    onSelectStyleCallback = { selectStyle ->
                        textRepeaterScreenViewModel.apply {
                            updateStyleList(selectStyle = selectStyle)
                            updateIsShowSelectStyleBottomSheet(isShowSelectStyleBottomSheet = false)
                        }
                    }
                )
            }

            if (state.isShowRepeatedText) {
                VerticalSpacer(verticalSpace = 18)
                RepeatTextResult(
                    repeatableText = state.repeatableText,
                    repeatCount = state.repeatCount?.toIntOrNull() ?: 1,
                    selectStyle = state.selectStyle,
                    isNewLineSwitchChecked = state.isNewLineSwitchChecked,
                    onStyleClickedCallback = {
                        textRepeaterScreenViewModel.updateIsShowSelectStyleBottomSheet(
                            isShowSelectStyleBottomSheet = true
                        )
                    },
                    onCheckedChange = {
                        textRepeaterScreenViewModel.updateIsNewLineSwitchChecked(isChecked = it)
                    },
                    onSaveCopyAndSendButtonsClicksCallback = {
                        when (it) {
                            TextRepeaterScreen.SAVE -> {
                                scope.launch {
                                    val isSaved = textRepeaterScreenViewModel.saveText()
                                    if (isSaved) {
                                        Toast.makeText(context, context.getString(R.string.saved_successfully_text), Toast.LENGTH_LONG).show()
                                        textRepeaterScreenViewModel.apply {
                                            updateRepeatableText(repeatableText = null)
                                            updateRepeatCount(repeatCount = null)
                                            updateIsNewLineSwitchChecked(isChecked = false)
                                            updateIsShowRepeatedText(isShowRepeatedText = false)
                                            resetStyle()
                                        }
                                    } else {
                                        Toast.makeText(context, context.getString(R.string.not_saved_successfully_text), Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                            TextRepeaterScreen.COPY -> textCopyController.copyText(textRepeaterScreenViewModel.getStyledText())
                            TextRepeaterScreen.SEND -> share(context, textRepeaterScreenViewModel.getStyledText())
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun RepeatTextResult(
    repeatableText: String? = null,
    repeatCount: Int? = null,
    selectStyle: SelectStyle? = null,
    isNewLineSwitchChecked: Boolean = false,
    onStyleClickedCallback: (() -> Unit)? = null,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    onSaveCopyAndSendButtonsClicksCallback: ((TextRepeaterScreen) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = ColorF7F7F7),
        shape = RoundedCornerShape(size = 20.sdp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.sdp, vertical = 14.sdp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 20.sdp))
                        .clickable {
                            onStyleClickedCallback?.invoke()
                        }
                        .border(
                            width = 1.sdp,
                            color = Color000000,
                            shape = RoundedCornerShape(size = 20.sdp)
                        )
                        .padding(horizontal = 10.sdp, vertical = 7.sdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 7.sdp)
                ) {
                    Text(
                        text = stringResource(id = R.string.style_text),
                        color = Color000000,
                        fontFamily = robotoMedium,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.ssp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.style_image),
                        contentDescription = null
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 12.sdp)
                ) {
                    Text(
                        text = stringResource(id = R.string.new_line_text),
                        fontFamily = sfCompactRoundedMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color000000,
                        fontSize = 14.ssp
                    )
                    CustomSwitch(
                        isChecked = isNewLineSwitchChecked
                    ) {
                        onCheckedChange?.invoke(it)
                    }
                }
            }
            VerticalSpacer(verticalSpace = 12)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 150.sdp)
                    .background(
                        color = ColorF3F2F7,
                        shape = RoundedCornerShape(size = 15.sdp)
                    )
                    .padding(all = 10.sdp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = if (isNewLineSwitchChecked) 1 else 2),
                    modifier = Modifier
                        .weight(weight = 1F)
                ) {
                    items(
                        count = repeatCount ?: 1
                    ) {
                        Text(
                            text = when (selectStyle?.fontName) {
                                robotoMedium -> "${repeatableText}${stringResource(id = R.string.first_style_emoji)}"
                                righteousRegular -> "${stringResource(id = R.string.second_style_emoji)}${repeatableText}${stringResource(id = R.string.second_style_emoji)}"
                                rochesterRegular -> "${stringResource(id = R.string.third_style_emoji)}${repeatableText}${stringResource(id = R.string.third_style_emoji)}"
                                rokkittMedium -> "${stringResource(id = R.string.fourth_style_emoji)}${repeatableText}${stringResource(id = R.string.fourth_style_emoji)}"
                                rougeScriptRegular -> "${stringResource(id = R.string.fifth_style_emoji)}${repeatableText}${stringResource(id = R.string.fifth_style_emoji)}"
                                romanescoRegular -> "${stringResource(id = R.string.sixth_style_emoji)}${repeatableText}${stringResource(id = R.string.sixth_style_emoji)}"
                                else -> ""
                            },
                            fontSize = 15.ssp,
                            fontFamily = selectStyle?.fontName,
                            color = Color000000
                        )
                        if (!isNewLineSwitchChecked)
                            HorizontalSpacer()
                    }
                }
                HorizontalSpacer()
                Image(
                    painter = painterResource(id = R.drawable.save_image),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onSaveCopyAndSendButtonsClicksCallback?.invoke(TextRepeaterScreen.SAVE)
                        }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.sdp),
                horizontalArrangement = Arrangement.spacedBy(space = 5.sdp)
            ) {
                MyCustomButton(
                    modifier = Modifier
                        .weight(weight = 1F),
                    buttonImage = R.drawable.copy_image,
                    buttonText = stringResource(id = R.string.copy_text),
                    buttonBackgroundColor = Color2FB042,
                    buttonTextFont = sfCompactRoundedMedium,
                    buttonClickCallback = {
                        onSaveCopyAndSendButtonsClicksCallback?.invoke(TextRepeaterScreen.COPY)
                    }
                )
                MyCustomButton(
                    modifier = Modifier
                        .weight(weight = 1F),
                    buttonImage = R.drawable.send_image,
                    buttonText = stringResource(id = R.string.send_text),
                    buttonBackgroundColor = Color1B75BB,
                    buttonTextFont = sfCompactRoundedMedium,
                    isForSendButton = true,
                    buttonClickCallback = {
                        onSaveCopyAndSendButtonsClicksCallback?.invoke(TextRepeaterScreen.SEND)
                    }
                )
            }
        }
    }
}