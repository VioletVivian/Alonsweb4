package cn.chitanda.music.ui

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import cn.chitanda.music.R
import cn.chitanda.music.ui.scene.login.LoginScene
import cn.chitanda.music.ui.scene.main.MainScene
import cn.chitanda.music.ui.scene.other.ThemeScene
import cn.chitanda.music.ui.scene.play_detail.PlayDetailScene
import cn.chitanda.music.ui.scene.playlist.PlaylistScene
import cn.chitanda.music.ui.scene.splash.SplashScene
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 *@author: Chen
 *@createTime: 2021/8/31 13:18
 *@description:
 **/
sealed class Scene(val id: String, @StringRes val label: Int? = null) {
    object Splash : Scene(id = "splash")
    object Main : Scene(id = "home", label = R.string.label_main)
