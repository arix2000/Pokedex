package com.arix.pokedex.core.base

import androidx.annotation.DrawableRes
import com.arix.pokedex.R

enum class AppIcons(val alias: String, @DrawableRes val iconId: Int) {
    DEFAULT("features.splash_activity.SplashActivityDefault", R.drawable.app_icon_red),
    BLUE("features.splash_activity.SplashActivityBlue", R.drawable.app_icon_blue),
    PURPLE("features.splash_activity.SplashActivityPurple", R.drawable.app_icon_purple),
    ORANGE("features.splash_activity.SplashActivityOrange", R.drawable.app_icon_orange),
    GREEN("features.splash_activity.SplashActivityGreen", R.drawable.app_icon_green);
}