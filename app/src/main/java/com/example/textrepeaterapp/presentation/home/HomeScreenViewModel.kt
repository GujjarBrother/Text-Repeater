package com.example.textrepeaterapp.presentation.home

import androidx.lifecycle.ViewModel
import com.example.textrepeaterapp.R
import com.example.textrepeaterapp.core.utils.ColorDCEEFA
import com.example.textrepeaterapp.core.utils.ColorEDF7FF
import com.example.textrepeaterapp.core.utils.ColorF2E3BD
import com.example.textrepeaterapp.core.utils.ColorFEDBEC
import com.example.textrepeaterapp.core.utils.ColorFEF6E0
import com.example.textrepeaterapp.core.utils.ColorFFF6FC
import com.example.textrepeaterapp.domain.models.HomeFeatures
import com.example.textrepeaterapp.domain.models.NavigationDrawer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeScreenState(
    val featuresList: List<HomeFeatures> = emptyList(),
    val navigationDrawerList: List<NavigationDrawer> = emptyList()
)

val featuresList = listOf(
    HomeFeatures(
        icon = R.drawable.quick_replies_image,
        iconBGColor = ColorF2E3BD,
        featureBGColor = ColorFEF6E0,
        title = R.string.quick_replies_text,
        description = R.string.send_repeated_texts_fast_text
    ),
    HomeFeatures(
        icon = R.drawable.saved_text_image,
        iconBGColor = ColorDCEEFA,
        featureBGColor = ColorEDF7FF,
        title = R.string.saved_text_text,
        description = R.string.access_saved_messages_text
    ),
    HomeFeatures(
        icon = R.drawable.recent_repititions_image,
        iconBGColor = ColorFEDBEC,
        featureBGColor = ColorFFF6FC,
        title = R.string.recent_repetitions_text,
        description = R.string.recently_repeated_messages_text
    )
)
val navigationDrawerList = listOf(
    NavigationDrawer(
        image = R.drawable.language_image,
        name = R.string.language_text,
        isShowSelectedLanguage = true,
        selectedLanguage = R.string.english_text
    ),
    NavigationDrawer(
        image = R.drawable.share_image,
        name = R.string.share_image,
        isShowArrow = false
    ),
    NavigationDrawer(
        image = R.drawable.rate_us_image,
        name = R.string.rate_us_text
    ),
    NavigationDrawer(
        image = R.drawable.feedback_image,
        name = R.string.feedback_text
    ),
    NavigationDrawer(
        image = R.drawable.privacy_policy_image,
        name = R.string.privacy_policy_text
    ),
    NavigationDrawer(
        image = R.drawable.version_image,
        name = R.string.version_text,
        isShowArrow = false,
        isShowVersionCode = true
    )
)

class HomeScreenViewModel : ViewModel() {

    private var _state = MutableStateFlow(value = HomeScreenState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                featuresList = featuresList,
                navigationDrawerList = navigationDrawerList
            )
        }
    }
}