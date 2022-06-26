
package cn.chitanda.music.ui.scene.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cn.chitanda.music.R
import cn.chitanda.music.http.bean.HomeBanner
import cn.chitanda.music.http.bean.HomeData
import cn.chitanda.music.http.bean.HomeRoundIcon
import cn.chitanda.music.http.bean.MLogExtInfo
import cn.chitanda.music.http.bean.SubTitleType
import cn.chitanda.music.ui.LocalMusicControllerBarHeight
import cn.chitanda.music.ui.LocalNavController
import cn.chitanda.music.ui.LocalUserViewModel
import cn.chitanda.music.ui.scene.PageState
import cn.chitanda.music.ui.theme.Shapes
import cn.chitanda.music.ui.widget.banner.Banner
import cn.chitanda.music.utils.collectPartAsState
import cn.chitanda.music.utils.toUnitString
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 *@author: Chen
 *@createTime: 2021/9/2 10:19
 *@description:
 **/
private const val TAG = "HomeScene"

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@SuppressLint("CheckResult")
@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun HomeScene(navController: NavController = LocalNavController.current, padding: PaddingValues) {
    val viewModel: HomeSceneViewModel = hiltViewModel()
    val user by LocalUserViewModel.current.user.collectAsState()
//    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
//    val scrollBehavior = remember(decayAnimationSpec) {
//    val scrollBehavior = remember {
//        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
//        TopAppBarDefaults.pinnedScrollBehavior()
//    }
    val viewState by viewModel.viewState.collectPartAsState(part = HomeViewState::state)
//    val appBarColors = TopAppBarDefaults.smallTopAppBarColors()
    val swiperRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    val banners by viewModel.viewState.collectPartAsState(part = HomeViewState::banner)
    val icons by viewModel.viewState.collectPartAsState(part = HomeViewState::icons)
    val playlist by viewModel.viewState.collectPartAsState(part = HomeViewState::playlist)
    val songList by viewModel.viewState.collectPartAsState(part = HomeViewState::songList)
    val mLog by viewModel.viewState.collectPartAsState(part = HomeViewState::mLog)

    Scaffold(
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("${stringResource(R.string.text_home_welcome_title)}${user.json?.data?.nickname}") },
//                scrollBehavior = scrollBehavior,
            )
        },
    ) {
        SwipeRefresh(modifier = Modifier.padding(it).padding(bottom = padding.calculateBottomPadding()), state = swiperRefreshState, onRefresh = {
            viewModel.loadHomeData()
        }) {
            CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = 16.dp,
                        bottom = LocalMusicControllerBarHeight.current.coerceAtLeast(16.dp)
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        HomeBanners(
                            banners,
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        )
                    }

                    item {
                        HomeRoundIconList(
                            icons,
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        )
                    }

                    item {
                        RecommendPlayList(
                            playlist,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(16.dp)
                        )
                    }

                    item {
                        RecommendSongList(
                            songList,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(16.dp)
                        )
                    }
                    item {
                        MLogList(
                            mLog,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(), contentPadding = PaddingValues(16.dp)
                        )
                    }

                }
            }
        }
    }
    val cxt = LocalContext.current
    LaunchedEffect(key1 = viewState) {
        when (viewState) {
            is PageState.Success -> swiperRefreshState.isRefreshing = false
            is PageState.Loading -> swiperRefreshState.isRefreshing = true
            is PageState.Empty -> viewModel.loadHomeData()
            is PageState.Error -> {
                swiperRefreshState.isRefreshing = false
                Toast.makeText(
                    cxt,
                    (viewState as PageState.Error).tr.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

}


@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
private fun HomeBanners(
    banners: List<HomeBanner.Banner>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    if (banners.isNotEmpty()) {
        Banner(
            data = banners,
            modifier = modifier,
            itemPaddingValues = contentPadding,
            itemSpacing = 16.dp,
            indicatorPaddingValues = PaddingValues(bottom = 8.dp)
        ) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(108 / 42f)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.BottomEnd
            ) {
                AsyncImage(
                    model = item.pic,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = null
                )
                Surface(
                    color = item.titleColor.color,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(topStart = 8.dp)
                ) {
                    Text(
                        text = item.typeTitle.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                    )
                }
            }
        }
    }

}

@ExperimentalCoilApi
@Composable
fun HomeRoundIconList(
    data: List<HomeRoundIcon>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    LazyRow(modifier = modifier, contentPadding = contentPadding) {
        itemsIndexed(data) { i, item ->
            Column(
                Modifier.fillMaxHeight() then if (i == data.lastIndex) Modifier else Modifier.padding(
                    end = 45.dp
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = item.iconUrl,
                    modifier = Modifier
                        .size(45.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            shape = CircleShape
                        ),
                    contentDescription = item.name,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)