package cn.chitanda.music.ui.widget.nestedscroll

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import kotlin.math.roundToInt

private const val TAG = "NestedScroll"

@Composable
fun rememberNestedScrollAppBarState(
    appBarHeight: Int,
    minHeight: Int,
    maxHeight: Int,
) = remember {
    NestedScrollAppBarState(appBarHeight, m