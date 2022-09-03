
package cn.chitanda.music.ui.scene.playlist

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import cn.chitanda.dynamicstatusbar.DynamicStatusBar
import cn.chitanda.music.R
import cn.chitanda.music.http.bean.Songs
import cn.chitanda.music.http.bean.artists
import cn.chitanda.music.http.paging.PlaylistSongsPagingSource
import cn.chitanda.music.ui.LocalMusicViewModel
import cn.chitanda.music.ui.LocalNavController
import cn.chitanda.music.ui.scene.PageState
import cn.chitanda.music.ui.scene.isLoading
import cn.chitanda.music.ui.theme.DownArcShape
import cn.chitanda.music.ui.theme.Shapes
import cn.chitanda.music.utils.toUnitString
import coil.compose.AsyncImage

/**
 * @author: Chen
 * @createTime: 2021/12/31 16:34
 * @description:
 **/
private const val TAG = "PlaylistScene"

@ExperimentalMaterial3Api
@Composable
fun PlaylistScene(navController: NavController = LocalNavController.current, playlist: String?) {
    if (playlist.isNullOrEmpty()) {
        navController.navigateUp()
        return
    }
    val musicViewModel = LocalMusicViewModel.current
    val viewModel = hiltViewModel<PlaylistViewModel>()
    val viewState by viewModel.viewState.collectAsState()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
//    val topAppBarScrollState = rememberTopAppBarState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val cxt = LocalContext.current
    LaunchedEffect(key1 = viewState) {
        if (viewState.state is PageState.Error) {
            Toast.makeText(cxt, (viewState.state as PageState.Error).tr.message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    Surface {
        Column(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            FoldableTopAppBar(
                scrollBehavior = scrollBehavior, viewState = viewState
            )
            //歌曲列表
            if (!viewState.state.isLoading && viewState.songs != null) {
                val pager = remember(viewState.songs) {
                    Log.d(TAG, "PlaylistScene: pager create")
                    Pager(
                        config = PagingConfig(pageSize = PlaylistSongsPagingSource.PageSize),
                        initialKey = 0,
                        pagingSourceFactory = { viewState.songs!! }
                    )
                }
                val lazyPagingItems = pager.flow.collectAsLazyPagingItems()
                val lazyState = rememberLazyListState()
                LazyColumn(
                    state = lazyState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = WindowInsets.navigationBars.union(
                        WindowInsets(top = 16.dp, bottom = 16.dp)
                    ).asPaddingValues()
                ) {
                    if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
                        item {
                            Text(
                                text = "Waiting for items to load from the backend",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }

                    itemsIndexed(lazyPagingItems) { index, item ->
//                        Text("Index=$index: ${item?.name}", fontSize = 14.sp)
                        item?.let {
                            SongsItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .clickable {
                                        musicViewModel.play(playlist, it.id.toString())
                                    }, song = it, index = index + 1,
                                iconColor = LocalContentColor.current.copy(alpha = 0.6f).copy(0.5f)
                            )
                        }
                    }

                    if (lazyPagingItems.loadState.append == LoadState.Loading) {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }

            }
            LaunchedEffect(key1 = viewState) {
                if (viewState.state == PageState.Success && viewState.songs == null) {
                    viewState.playlist?.let {
                        viewModel.getPlaylistSongsPagingSource(it.id, maxSize = it.songsCount)
                    }
                }
            }
        }
    }
    LaunchedEffect(key1 = playlist) {