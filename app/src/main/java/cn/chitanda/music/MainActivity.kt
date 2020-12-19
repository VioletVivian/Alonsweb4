package cn.chitanda.music

import android.os.Build
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import cn.chitanda.music.ui.LocalMusicViewModel
import cn.chitanda.music.ui.LocalThemeViewModel
import cn.chitanda.music.ui.LocalUserViewModel
import cn.chitanda.music.ui.Router
import cn.chitanda.music.ui.scene.LocaleUserViewModel
import cn.chitanda.music.ui.scene.ThemeViewModel
import cn.chitanda.music.ui.theme.MusicTheme
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

private const