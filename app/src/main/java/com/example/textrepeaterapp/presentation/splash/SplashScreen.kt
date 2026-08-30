package com.example.textrepeaterapp.presentation.splash

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.textrepeaterapp.R
import com.example.textrepeaterapp.core.utils.Color505050
import com.example.textrepeaterapp.core.utils.Color2FB042
import com.example.textrepeaterapp.core.utils.Color848484
import com.example.textrepeaterapp.core.utils.ColorFFFFFF
import com.example.textrepeaterapp.core.utils.VerticalSpacer
import com.example.textrepeaterapp.core.utils.sfProRoundedRegular
import com.example.textrepeaterapp.core.utils.sfProRoundedSemiBold
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun SplashScreen(
    navigateToNext: (() -> Unit)? = null
) {
    LaunchedEffect(Unit) {
        delay(timeMillis = 5000)
        navigateToNext?.invoke()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorFFFFFF)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(bottom = 20.sdp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1F),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_icon),
                contentDescription = null
            )
            VerticalSpacer(verticalSpace = 20)
            Text(
                text = stringResource(id = R.string.text_repeater_text),
                color = Color505050,
                fontFamily = sfProRoundedSemiBold,
                fontSize = 20.ssp,
                fontWeight = FontWeight.SemiBold
            )
            VerticalSpacer(verticalSpace = 5)
            Text(
                text = stringResource(id = R.string.quickly_repeat_any_text_many_times_text),
                color = Color848484,
                fontFamily = sfProRoundedRegular,
                fontSize = 13.ssp,
                fontWeight = FontWeight.Normal
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LoadingDots()
        }
    }
}

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun LoadingDots(
    modifier: Modifier = Modifier,
    dotCount: Int = 4,
    dotSize: Dp = 10.dp,
    color: Color = Color2FB042,
    bounceHeight: Dp = 8.dp,
    dotAnimationDuration: Int = 500,
    delayBetweenDots: Int = 150,
    pauseAfterCycle: Int = 800
) {
    val offsets = remember {
        List(dotCount) {
            Animatable(initialValue = 0.dp, typeConverter = Dp.VectorConverter)
        }
    }
    val alphas = remember {
        List(dotCount) {
            Animatable(initialValue = 0.5F)
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            for (i in 0 until dotCount) {
                launch {
                    offsets[i].animateTo(
                        targetValue = -bounceHeight,
                        animationSpec = tween(durationMillis = dotAnimationDuration / 2, easing = EaseInOutCubic)
                    )
                    offsets[i].animateTo(
                        targetValue = 0.dp,
                        animationSpec = tween(durationMillis = dotAnimationDuration / 2, easing = EaseInOutCubic)
                    )
                }
                launch {
                    alphas[i].animateTo(
                        targetValue = 1F,
                        animationSpec = tween(durationMillis = dotAnimationDuration / 2, easing = EaseInOutCubic)
                    )
                    alphas[i].animateTo(
                        targetValue = 0.3F,
                        animationSpec = tween(durationMillis = dotAnimationDuration / 2, easing = EaseInOutCubic)
                    )
                }
                delay(timeMillis = delayBetweenDots.toLong())
            }
            delay(timeMillis = pauseAfterCycle.toLong())
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 6.sdp)
    ) {
        for (i in 0 until dotCount) {
            Box(
                modifier = Modifier
                    .size(size = dotSize)
                    .offset(y = offsets[i].value)
                    .alpha(alpha = alphas[i].value)
                    .background(color = color, shape = CircleShape)
            ) { }
        }
    }
}