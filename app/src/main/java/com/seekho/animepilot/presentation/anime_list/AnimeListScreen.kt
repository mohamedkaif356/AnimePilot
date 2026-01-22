package com.seekho.animepilot.presentation.anime_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import com.seekho.animepilot.presentation.ui.component.AnimeCard
import com.seekho.animepilot.presentation.ui.component.AnimeListShimmer
import com.seekho.animepilot.presentation.ui.component.EmptyState
import com.seekho.animepilot.presentation.ui.component.ErrorState
import com.seekho.animepilot.presentation.ui.component.OfflineBanner
import com.seekho.animepilot.presentation.ui.component.RetryButton
import com.seekho.animepilot.core.util.ErrorMapper
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.material3.MaterialTheme
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.res.stringResource
import com.seekho.animepilot.R
import com.seekho.animepilot.core.domain.model.Anime
import com.seekho.animepilot.presentation.NavigationEvent
import com.seekho.animepilot.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AnimeListScreen(
    navController: NavController,
    viewModel: AnimeListViewModel = hiltViewModel()
) {
    val animePagingItems = viewModel.animePagingFlow.collectAsLazyPagingItems()
    val isOffline by viewModel.isOffline.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    
    // Handle navigation events
    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { event ->
            when (event) {
                is NavigationEvent.NavigateToDetail -> {
                    navController.navigate("anime_detail/${event.animeId}")
                }
            }
        }
    }
    
    // Sync refresh state with paging load state
    LaunchedEffect(animePagingItems.loadState.refresh) {
        val isRefreshingNow = animePagingItems.loadState.refresh is LoadState.Loading
        viewModel.setRefreshing(isRefreshingNow)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            if (!isOffline) animePagingItems.refresh()
        }
    )
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.screen_title_top_anime),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { paddingValues ->
        val contentAlpha by animateFloatAsState(
            targetValue = if (isRefreshing) 0.95f else 1.0f,
            label = "refresh_content_alpha"
        )
        val contentScale by animateFloatAsState(
            targetValue = if (isRefreshing) 0.98f else 1.0f,
            label = "refresh_content_scale"
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pullRefresh(
                    state = pullRefreshState,
                    enabled = !isOffline
                )
                .alpha(contentAlpha)
                .scale(contentScale)
        ) {

            val stateKey = when (animePagingItems.loadState.refresh) {
                is LoadState.Error if animePagingItems.itemCount == 0 -> "error"
                is LoadState.Loading if animePagingItems.itemCount == 0 && !isOffline -> "loading"
                else -> "content"
            }

            AnimatedContent(
                targetState = stateKey,
                transitionSpec = {
                    (fadeIn(tween(300, easing = FastOutSlowInEasing)) +
                            scaleIn(initialScale = 0.95f))
                        .togetherWith(
                            fadeOut(tween(300, easing = FastOutSlowInEasing)) +
                                    scaleOut(targetScale = 0.98f)
                        )
                },
                label = "list_state_transition"
            ) { state ->
                when (state) {
                    "error" -> {
                        val error = animePagingItems.loadState.refresh as LoadState.Error
                        val appError = ErrorMapper.map(error.error)
                        ErrorState(
                            title = ErrorMapper.getErrorTitle(appError),
                            message = ErrorMapper.toUserMessage(appError),
                            canRetry = true,
                            onRetry = { animePagingItems.retry() }
                        )
                    }

                    "loading" -> {
                        AnimeListShimmer(modifier = Modifier.fillMaxSize())
                    }

                    else -> {
                        AnimeListContent(
                            animePagingItems = animePagingItems,
                            isOffline = isOffline,
                            onAnimeClick = { viewModel.onAnimeClicked(it) }
                        )
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
private fun AnimeListContent(
    animePagingItems: LazyPagingItems<Anime>,
    isOffline: Boolean,
    onAnimeClick: (Int) -> Unit
) {
    Column {
        AnimatedVisibility(
            visible = isOffline,
            enter = fadeIn(tween(300)) + slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(300)
            ),
            exit = fadeOut(tween(300)) + slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(300)
            )
        ) {
            OfflineBanner(modifier = Modifier.fillMaxWidth())
        }
        
        LazyColumn(
            contentPadding = PaddingValues(Dimens.spaceL),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceM)
        ) {
            items(
                count = animePagingItems.itemCount,
                key = animePagingItems.itemKey { it.id },
                contentType = animePagingItems.itemContentType { "anime" }
            ) { index ->
                val anime = animePagingItems[index]
                if (anime != null) {
                    AnimeCard(
                        anime = anime,
                        onClick = { onAnimeClick(anime.id) },
                        isOffline = isOffline
                    )
                }
            }
            
            // Show loading indicator at the bottom when loading more
            if (animePagingItems.loadState.append is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimens.spaceL),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            
            // Show error state at the bottom if append failed
            if (animePagingItems.loadState.append is LoadState.Error) {
                item {
                    val error = animePagingItems.loadState.append as LoadState.Error
                    val appError = ErrorMapper.map(error.error)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimens.spaceL),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(Dimens.spaceM)
                    ) {
                        Text(
                            text = ErrorMapper.toUserMessage(appError),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        RetryButton(
                            onClick = { animePagingItems.retry() }
                        )
                    }
                }
            }
            
            // Show end of list indicator when all items are loaded
            if (animePagingItems.loadState.append !is LoadState.Loading &&
                animePagingItems.loadState.append !is LoadState.Error &&
                animePagingItems.itemCount > 0) {
                item {
                    Text(
                        text = stringResource(R.string.status_end_of_list),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimens.spaceL),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
            
            // Show empty state
            if (animePagingItems.loadState.refresh !is LoadState.Loading &&
                animePagingItems.loadState.refresh !is LoadState.Error &&
                animePagingItems.itemCount == 0) {
                item {
                    EmptyState(message = stringResource(R.string.status_no_anime_available))
                }
            }
        }
    }
}
