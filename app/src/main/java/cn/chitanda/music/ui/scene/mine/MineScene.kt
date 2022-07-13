
package cn.chitanda.music.ui.scene.mine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.chitanda.music.R
import cn.chitanda.music.http.DataState
import cn.chitanda.music.http.bean.PlaylistJson
import cn.chitanda.music.http.bean.UserProfile
import cn.chitanda.music.http.isLoading
import cn.chitanda.music.ui.LocalMusicControllerBarHeight
import cn.chitanda.music.ui.LocalNavController
import cn.chitanda.music.ui.LocalUserViewModel
import cn.chitanda.music.ui.Scene
import cn.chitanda.music.ui.scene.LocaleUserViewModel
import cn.chitanda.music.ui.theme.Shapes
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 *@author: Chen
 *@createTime: 2021/9/2 10:18
 *@description:
 **/

private const val TAG = "MineScene"

@ExperimentalMaterial3Api
@ExperimentalCoilApi
@Composable
fun MineScene(
    navController: NavController = LocalNavController.current,
    viewModel: LocaleUserViewModel = LocalUserViewModel.current,
    padding: PaddingValues
) {
    val playlist by viewModel.playlist.collectAsState()
    val user by viewModel.user.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
//    Scaffold { padding ->