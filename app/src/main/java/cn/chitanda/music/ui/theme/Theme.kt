package cn.chitanda.music.ui.theme

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.IntRange
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import cn.chitanda.music.ui.monet.theme.MonetColor

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme()

@SuppressLint("NewApi")
@Composable
fun MusicTheme(
    customColor: MonetColor? = null,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        dynamicColor && darkTheme && customColor == null -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme && customColor == null -> dynamicLightColorScheme(LocalContext.current)
        customColor != null && darkTheme -> customColor.darkMonetColorScheme()
        customColor != null && !darkTheme -> customColor.lightMonetColorScheme()
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

androidx.compose.material3.Divider()

    MaterialTheme(
        colorScheme = colors.animateColor(),
        typography = Typography,
    ) {
        androidx.compose.material.MaterialTheme(
            colors = Colors(
                primary = colors.primary,
                primaryVariant = colors.inversePrimary,
                secondary = colors.secondary,
                onSecondary = colors.onSecondary,
                secondaryVariant = colors.secondaryContainer,
                background = colors.background,
                onBackground = colors.onBackground,
                surface = colors.surface, error = colors.error,
                onPrimary = colors.onPrimary,
                onSurface = colors.onSurface,
                onError = colors.onError, isLight = darkTheme
            ),
            shapes = Shapes, content = content
        )
    }
}

@Composable
fun ColorScheme.animateColor() = ColorScheme(
    primary = animateColorAsState(targetValue = this.primary, tween(600)).value,
    onPrimary = animateColorAsState(targetValue = this.onPrimary, tween(600)).value,
    primaryContainer = animateColorAsState(targetValue = this.primaryContainer, tween(600)).value,
    onPrimaryContainer = animateColorAsState(
        targetValue = this.onPrimaryContainer,
        tween(600)
    ).value,
    inversePrimary = animateColorAsState(targetValue = this.inversePrimary, tween(600)).value,
    secondary = animateColorAsState(targetValue = this.secondary, tween(600)).value,
    onSecondary = animateColorAsState(targ