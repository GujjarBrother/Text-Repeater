package com.example.textrepeaterapp.presentation.home

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.textrepeaterapp.R
import com.example.textrepeaterapp.core.utils.Color000000
import com.example.textrepeaterapp.core.utils.Color2FB042
import com.example.textrepeaterapp.core.utils.Color505050
import com.example.textrepeaterapp.core.utils.Color676767
import com.example.textrepeaterapp.core.utils.Color717171
import com.example.textrepeaterapp.core.utils.Color7A7F7C
import com.example.textrepeaterapp.core.utils.Color808080
import com.example.textrepeaterapp.core.utils.Color848484
import com.example.textrepeaterapp.core.utils.ColorEAE5FF
import com.example.textrepeaterapp.core.utils.ColorEFEFEF
import com.example.textrepeaterapp.core.utils.ColorF2F2F2
import com.example.textrepeaterapp.core.utils.ColorF4F2FF
import com.example.textrepeaterapp.core.utils.ColorF6F6FF
import com.example.textrepeaterapp.core.utils.ColorFEC006
import com.example.textrepeaterapp.core.utils.ColorFFFFFF
import com.example.textrepeaterapp.core.utils.HorizontalSpacer
import com.example.textrepeaterapp.core.utils.TextRepeaterToolbar
import com.example.textrepeaterapp.core.utils.ToolbarActions
import com.example.textrepeaterapp.core.utils.VerticalSpacer
import com.example.textrepeaterapp.core.utils.robotoMedium
import com.example.textrepeaterapp.core.utils.robotoRegular
import com.example.textrepeaterapp.core.utils.sfCompactRoundedMedium
import com.example.textrepeaterapp.core.utils.sfProRoundedMedium
import com.example.textrepeaterapp.core.utils.sfProRoundedRegular
import com.example.textrepeaterapp.core.utils.sfProRoundedSemiBold
import com.example.textrepeaterapp.domain.models.HomeFeatures
import kotlinx.coroutines.launch
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = koinViewModel(),
    textRepeaterCallback: (() -> Unit)? = null,
    quickRepliesCallback: (() -> Unit)? = null
) {
    val state = homeScreenViewModel.state.collectAsState().value
    val context = LocalContext.current
    val activity = LocalActivity.current as Activity
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BackHandler {
        if (drawerState.isOpen) {
            scope.launch {
                drawerState.close()
            }
        } else activity.finishAndRemoveTask()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .windowInsetsPadding(insets = WindowInsets.systemBars)
                    .width(width = 230.sdp),
                drawerContainerColor = ColorFFFFFF,
                drawerShape = RoundedCornerShape(topEnd = 30.sdp, bottomEnd = 30.sdp)
            ) {
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 50.sdp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(width = 50.sdp, height = 50.sdp),
                        painter = painterResource(id = R.drawable.splash_icon),
                        contentDescription = null
                    )
                    VerticalSpacer(verticalSpace = 10)
                    Text(
                        text = stringResource(id = R.string.text_repeater_text),
                        color = Color505050,
                        fontFamily = sfProRoundedSemiBold,
                        fontSize = 16.ssp,
                        fontWeight = FontWeight.SemiBold
                    )
                    VerticalSpacer(verticalSpace = 3)
                    Text(
                        text = stringResource(id = R.string.quickly_repeat_any_text_many_times_text),
                        color = Color848484,
                        fontFamily = sfProRoundedRegular,
                        fontSize = 12.ssp,
                        fontWeight = FontWeight.Normal
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .padding(start = 20.sdp, top = 30.sdp, end = 20.sdp, bottom = 10.sdp),
                    thickness = 0.5.sdp,
                    color = Color7A7F7C
                )
                LazyColumn {
                    items(items = state.navigationDrawerList) {
                        NavigationDrawerItem(
                            modifier = Modifier
                                .padding(horizontal = 5.sdp),
                            label = {
                                Text(
                                    text = stringResource(id = it.name),
                                    fontSize = 13.ssp,
                                    color = Color717171,
                                    fontFamily = sfCompactRoundedMedium,
                                    fontWeight = FontWeight.Medium
                                )
                            },
                            selected = false,
                            onClick = {
                                Toast.makeText(context, "SAG...!", Toast.LENGTH_LONG).show()
                            },
                            icon = {
                                Image(
                                    painter = painterResource(id = it.image),
                                    contentDescription = null
                                )
                            },
                            badge = {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(space = 8.sdp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (it.isShowSelectedLanguage && it.isShowArrow) {
                                        Text(
                                            text = stringResource(id = it.selectedLanguage),
                                            fontSize = 11.ssp,
                                            color = Color2FB042,
                                            fontFamily = robotoMedium,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                    if (it.isShowArrow) {
                                        Image(
                                            painter = painterResource(id = R.drawable.forward_arrow_image),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(color = Color2FB042)
                                        )
                                    }
                                    if (it.isShowVersionCode) {
                                        Text(
                                            text = "v1.0.1",
                                            fontSize = 11.ssp,
                                            color = Color7A7F7C,
                                            fontFamily = robotoRegular,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TextRepeaterToolbar(
                    backArrow = R.drawable.hamburger_image,
                    isBackArrowIcon = false,
                    toolbarActions = ToolbarActions(
                        firstAction = {
                            Image(
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .background(color = ColorFEC006)
                                    .clickable {
                                        Toast.makeText(
                                            context,
                                            "Premium is clicked...!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    .padding(horizontal = 8.sdp, vertical = 10.sdp),
                                painter = painterResource(id = R.drawable.premium_crown_image),
                                contentDescription = null
                            )
                        }
                    ),
                    backIconClickCallback = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
                    .background(color = ColorFFFFFF)
                    .padding(bottom = 10.sdp)
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.sdp, top = 10.sdp, end = 10.sdp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        border = BorderStroke(width = 1.2.sdp, color = ColorEFEFEF),
                        shape = RoundedCornerShape(size = 20.sdp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            ColorF6F6FF,
                                            ColorF4F2FF,
                                            ColorEAE5FF
                                        )
                                    )
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .height(intrinsicSize = IntrinsicSize.Min),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(start = 14.sdp)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.text_repeater_text),
                                        color = Color000000,
                                        fontSize = 16.ssp,
                                        fontFamily = sfProRoundedSemiBold,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    VerticalSpacer(verticalSpace = 5)
                                    Text(
                                        text = stringResource(id = R.string.home_screen_text_repeater_description_text),
                                        color = Color676767,
                                        fontSize = 10.ssp,
                                        fontFamily = sfProRoundedRegular,
                                        lineHeight = 13.ssp,
                                        fontWeight = FontWeight.Normal
                                    )
                                    VerticalSpacer(verticalSpace = 18)
                                    Button(
                                        shape = CircleShape,
                                        colors = ButtonDefaults.buttonColors(containerColor = Color000000),
                                        onClick = {
                                            textRepeaterCallback?.invoke()
                                        }
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.repeat_text_text),
                                            color = ColorFFFFFF,
                                            fontFamily = sfProRoundedMedium,
                                            fontSize = 12.ssp,
                                            fontWeight = FontWeight.Medium
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.forward_arrow_image),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .padding(start = 10.sdp)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .padding(top = 5.sdp),
                                        painter = painterResource(id = R.drawable.home_hellos_image),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    VerticalSpacer(verticalSpace = 15)
                }
                items(items = state.featuresList) { features ->
                    FeaturesOptions(features = features) {
                        when (features.title) {
                            R.string.quick_replies_text -> quickRepliesCallback?.invoke()
                            R.string.saved_text_text -> {}
                            R.string.recent_repetitions_text -> {}
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FeaturesOptions(
    features: HomeFeatures,
    clickCallback: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.sdp, vertical = 5.sdp)
            .clip(shape = CircleShape)
            .background(color = features.featureBGColor ?: Color.Transparent)
            .border(
                border = BorderStroke(width = 1.2.sdp, color = ColorF2F2F2),
                shape = CircleShape
            )
            .clickable {
                clickCallback.invoke()
            }
            .padding(all = 4.sdp)
    ) {
        Row(
            modifier = Modifier
                .padding(end = 15.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .background(
                        color = features.iconBGColor ?: Color.Transparent,
                        shape = CircleShape
                    )
                    .padding(all = 10.sdp),
                painter = painterResource(id = features.icon),
                contentDescription = null
            )
            HorizontalSpacer()
            Column(
                modifier = Modifier
                    .weight(weight = 1F)
            ) {
                Text(
                    text = stringResource(id = features.title),
                    color = Color000000,
                    fontFamily = sfProRoundedMedium,
                    fontSize = 15.ssp,
                    fontWeight = FontWeight.Medium
                )
                VerticalSpacer(verticalSpace = 5)
                Text(
                    text = stringResource(id = features.description),
                    color = Color808080,
                    fontFamily = sfProRoundedRegular,
                    fontSize = 10.ssp,
                    fontWeight = FontWeight.Normal
                )
            }
            Image(
                modifier = Modifier
                    .background(color = ColorFFFFFF, shape = CircleShape)
                    .padding(all = 5.sdp),
                painter = painterResource(id = R.drawable.arrow_image),
                contentDescription = null
            )
        }
    }
}