package com.example.textrepeaterapp.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.textrepeaterapp.R
import com.example.textrepeaterapp.domain.models.SelectStyle
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun HorizontalSpacer(horizontalSpace: Int = 10) {
    Spacer(
        modifier = Modifier
            .width(width = horizontalSpace.sdp)
    )
}

@Composable
fun VerticalSpacer(verticalSpace: Int = 10) {
    Spacer(
        modifier = Modifier
            .height(height = verticalSpace.sdp)
    )
}

data class ToolbarActions(
    var firstAction: @Composable (() -> Unit)? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextRepeaterToolbar(
    title: String? = null,
    backArrow: Int? = null,
    isBackArrowIcon: Boolean = true,
    toolbarActions: ToolbarActions? = null,
    backIconClickCallback: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = Modifier
            .padding(horizontal = 5.sdp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ColorFFFFFF
        ),
        title = {
            title?.let {
                if (it.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(start = 5.sdp),
                        text = it,
                        color = Color000000,
                        fontSize = 16.ssp,
                        fontFamily = robotoMedium
                    )
                }
            }
        },
        navigationIcon = {
            backArrow?.let {
                Image(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .background(color = ColorF5F5F5)
                        .clickable {
                            backIconClickCallback?.invoke()
                        }
                        .padding(
                            horizontal = if (isBackArrowIcon) 13.sdp else 10.sdp,
                            vertical = if (isBackArrowIcon) 10.sdp else 12.sdp
                        ),
                    painter = painterResource(id = backArrow),
                    contentDescription = null
                )
            }
        },
        actions = {
            toolbarActions?.let {
                it.firstAction?.invoke()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectStyleBottomSheet(
    selectStyleList: List<SelectStyle> = emptyList(),
    onDismissCallback: (() -> Unit)? = null,
    onSelectStyleCallback: ((SelectStyle) -> Unit)? = null
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            it != SheetValue.Hidden
        }
    )
    ModalBottomSheet(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .navigationBarsPadding(),
        sheetState = bottomSheetState,
        dragHandle = null,
        containerColor = ColorFFFFFF,
        shape = RoundedCornerShape(topStart = 20.sdp, topEnd = 20.sdp),
        onDismissRequest = {
            onDismissCallback?.invoke()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 15.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.change_style_text),
                fontFamily = sfCompactRoundedSemiBold,
                fontSize = 17.ssp,
                fontWeight = FontWeight.SemiBold,
                color = Color000000
            )
            VerticalSpacer(verticalSpace = 20)
            LazyColumn {
                itemsIndexed(items = selectStyleList) { _, selectStyle ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.sdp)
                            .clip(shape = CircleShape)
                            .background(color = ColorF3F2F7)
                            .clickable {
                                onSelectStyleCallback?.invoke(selectStyle)
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 10.sdp),
                            horizontalArrangement = Arrangement.spacedBy(space = 5.sdp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(weight = 1F),
                                text = stringResource(id = selectStyle.text),
                                fontFamily = selectStyle.fontName,
                                fontSize = 15.ssp,
                                color = Color000000
                            )
                            if (selectStyle.isSelected) {
                                Image(
                                    painter = painterResource(id = R.drawable.tick_image),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(shape = CircleShape)
                                        .background(color = Color36B13E)
                                        .padding(horizontal = 4.sdp, vertical = 5.sdp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun CustomSwitch(
    isChecked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null
) {
    val thumbOffSet by animateDpAsState(
        targetValue = if (isChecked) 18.sdp else 2.sdp,
        label = ""
    )
    val trackColor by animateColorAsState(
        targetValue = if (isChecked) Color81C784 else  ColorE5E5EA,
        label = ""
    )
    Box(
        modifier = Modifier
            .width(width = 40.sdp)
            .height(height = 25.sdp)
            .clip(shape = CircleShape)
            .background(color = trackColor)
            .clickable {
                onCheckedChange?.invoke(!isChecked)
            }
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffSet)
                .size(size = 20.sdp)
                .shadow(
                    elevation = 5.sdp,
                    shape = CircleShape
                )
                .clip(shape = CircleShape)
                .background(color = ColorFFFFFF)
                .align(alignment = Alignment.CenterStart)
        )
    }
}

@Composable
fun MyCustomButton(
    modifier: Modifier = Modifier,
    buttonImage: Int = 0,
    buttonText: String? = null,
    buttonBackgroundColor: Color? = null,
    buttonTextFont: FontFamily? = null,
    isForSendButton: Boolean = false,
    buttonClickCallback: (() -> Unit)? = null
) {
    Button(
        modifier = modifier,
        onClick = {
            buttonClickCallback?.invoke()
            },
        colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor ?: Color2FB042)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 6.sdp),
            horizontalArrangement = Arrangement.spacedBy(space = 15.sdp)
        ) {
            if (!isForSendButton) {
                Image(
                    painter = painterResource(id = buttonImage),
                    contentDescription = null
                )
            }
            Text(
                text = buttonText ?: "",
                color = ColorFFFFFF,
                fontFamily = buttonTextFont,
                fontWeight = FontWeight.Medium,
                fontSize = 12.ssp
            )
            if (isForSendButton) {
                Image(
                    painter = painterResource(id = buttonImage),
                    contentDescription = null
                )
            }
        }
    }
}

fun share(context: Context, textToBeShared: String) {
    Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, textToBeShared)
        if (resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(this, "Share Via!"))
        } else {
            Toast.makeText(context, context.getString(R.string.share_toast_text), Toast.LENGTH_LONG).show()
        }
    }
}