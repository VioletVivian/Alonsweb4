package cn.chitanda.music.ui.scene.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cn.chitanda.music.http.DataState
import cn.chitanda.music.ui.LocalNavController
import cn.chitanda.music.ui.LocalUserViewModel
import cn.chitanda.music.ui.Scene
import cn.chitanda.music.ui.scene.LocaleUserViewModel

/**
 *@author: Chen
 *@createTime: 2021/8/31 13:22
 *@description:
 **/
@Composable
fun SplashScene(
    userViewModel: LocaleUserViewModel = LocalUserViewModel.current,
    navController: NavController = LocalNavController.current
) {
    val user by userViewModel.user.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    )
    LaunchedEffect(key1 = user) {
        when (user.status) {
           