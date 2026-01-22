package com.seekho.animepilot.presentation.anime_detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.seekho.animepilot.presentation.ui.component.AnimeDetailShimmer
import com.seekho.animepilot.presentation.ui.component.ChipSectionRow
import com.seekho.animepilot.presentation.ui.component.ErrorState
import com.seekho.animepilot.presentation.ui.component.TrailerPlayer
import androidx.compose.ui.res.stringResource
import com.seekho.animepilot.R
import com.seekho.animepilot.core.domain.model.AnimeDetail
import com.seekho.animepilot.presentation.ui.component.AnimePosterLarge
import com.seekho.animepilot.presentation.ui.component.OfflineBanner
import com.seekho.animepilot.ui.theme.Dimens
import com.seekho.animepilot.ui.theme.Elevation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    animeId: Int,
    navController: NavController? = null,
    viewModel: AnimeDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isOffline by viewModel.isOffline.collectAsStateWithLifecycle()
    
    LaunchedEffect(animeId) {
        viewModel.loadAnimeDetail(animeId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.screen_title_anime_details),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    if (navController != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = stringResource(R.string.nav_back)
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AnimatedContent(
                targetState = uiState,
                transitionSpec = {
                    (fadeIn(tween(300, easing = FastOutSlowInEasing)) + 
                     scaleIn(initialScale = 0.95f, animationSpec = tween(300, easing = FastOutSlowInEasing)))
                        .togetherWith(
                            fadeOut(tween(300, easing = FastOutSlowInEasing)) + 
                            scaleOut(targetScale = 0.98f, animationSpec = tween(300, easing = FastOutSlowInEasing))
                        )
                },
                label = "detail_state_transition"
            ) { state ->
                when (state) {
                    is AnimeDetailUiState.Loading -> {
                        AnimeDetailShimmer(modifier = Modifier.fillMaxSize())
                    }
                    
                    is AnimeDetailUiState.Success -> {
                        AnimeDetailWithPullRefresh(
                            animeDetail = state.animeDetail,
                            isRefreshing = state.isRefreshing,
                            isOffline = isOffline,
                            onRefresh = { viewModel.refresh() }
                        )
                    }
                    
                    is AnimeDetailUiState.Error -> {
                        if (state.cachedData != null) {
                            AnimeDetailWithPullRefresh(
                                animeDetail = state.cachedData,
                                isRefreshing = false,
                                isOffline = true,
                                onRefresh = { viewModel.retry() }
                            )
                        } else {
                            ErrorState(
                                title = state.errorTitle,
                                message = state.errorMessage,
                                canRetry = state.canRetry,
                                onRetry = { viewModel.retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AnimeDetailWithPullRefresh(
    animeDetail: AnimeDetail,
    isRefreshing: Boolean,
    isOffline: Boolean,
    onRefresh: () -> Unit
) {
    val contentAlpha by animateFloatAsState(
        targetValue = if (isRefreshing) 0.95f else 1f,
        label = "refresh_content_alpha"
    )
    val contentScale by animateFloatAsState(
        targetValue = if (isRefreshing) 0.98f else 1f,
        label = "refresh_content_scale"
    )

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            if (!isOffline) onRefresh()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(
                state = pullRefreshState,
                enabled = !isOffline
            )
    ) {
        AnimeDetailContent(
            animeDetail = animeDetail,
            isOffline = isOffline,
            modifier = Modifier
                .alpha(contentAlpha)
                .scale(contentScale)
        )

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun AnimeDetailContent(
    animeDetail: AnimeDetail,
    isOffline: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.cardContentPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceL)
    ) {
        if (isOffline) {
            OfflineBanner(modifier = Modifier.fillMaxWidth())
        }

        if (animeDetail.trailerUrl != null) {
            TrailerPlayer(
                trailerUrl = animeDetail.trailerUrl,
                posterUrl = animeDetail.posterUrl,
                contentDescription = animeDetail.title,
                modifier = Modifier.fillMaxWidth(),
                isOffline = isOffline,
                onPlayerError = {
                    // Error handled internally with fallback to poster
                }
            )
        } else if (animeDetail.posterUrl != null) {
            AnimePosterLarge(
                url = animeDetail.posterUrl,
                contentDescription = animeDetail.title
            )
        }

        Text(
            text = animeDetail.title,
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.spaceL)
        ) {
            if (animeDetail.rating != null) {
                Text(
                    text = stringResource(R.string.anime_rating, animeDetail.rating.toString()),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            animeDetail.episodes?.let { episodes ->
                Text(
                    text = stringResource(R.string.anime_episodes, episodes),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        if (animeDetail.synopsis.isNotBlank()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = Elevation.cardDefault)
            ) {
                Column(
                    modifier = Modifier.padding(Dimens.cardContentPadding),
                    verticalArrangement = Arrangement.spacedBy(Dimens.spaceS)
                ) {
                    Text(
                        text = stringResource(R.string.anime_synopsis),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = animeDetail.synopsis,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        ChipSectionRow(
            title = stringResource(R.string.anime_genres),
            items = animeDetail.genres,
            modifier = Modifier.fillMaxWidth()
        )

        ChipSectionRow(
            title = stringResource(R.string.anime_cast),
            items = animeDetail.cast,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
