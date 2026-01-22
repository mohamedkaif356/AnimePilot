package com.seekho.animepilot.presentation.ui.component

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.seekho.animepilot.R
import com.seekho.animepilot.presentation.util.YouTubeUtils

private sealed class TrailerPlayerState {
    object Loading : TrailerPlayerState()
    object Playing : TrailerPlayerState()
    data class Restricted(val videoId: String) : TrailerPlayerState()
    data class Error(val message: String) : TrailerPlayerState()
}

@Composable
fun TrailerPlayer(
    trailerUrl: String?,
    posterUrl: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    isOffline: Boolean = false,
    onPlayerError: () -> Unit = {}
) {
    val videoId = remember(trailerUrl) {
        YouTubeUtils.extractVideoId(trailerUrl)
    }
    val siParameter = remember(trailerUrl) {
        YouTubeUtils.extractSiParameter(trailerUrl)
    }
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    var state by remember(videoId) {
        mutableStateOf<TrailerPlayerState>(TrailerPlayerState.Loading)
    }
    var playerView: YouTubePlayerView? by remember { mutableStateOf(null) }

    // If no valid video ID, show poster
    if (videoId == null) {
        AnimePosterLarge(
            url = posterUrl, contentDescription = contentDescription, modifier = modifier
        )
        return
    }

    val context = LocalContext.current

    // Preload poster when we have trailer + poster and we're online
    LaunchedEffect(posterUrl, videoId, isOffline) {
        if (posterUrl != null && !isOffline) {
            try {
                val request = ImageRequest.Builder(context).data(posterUrl)
                    .diskCachePolicy(CachePolicy.ENABLED).memoryCachePolicy(CachePolicy.ENABLED)
                    .build()
                context.imageLoader.execute(request)
            } catch (_: Exception) {
                // Silently fail - preload is best effort
            }
        }
    }

    // If offline, show poster immediately instead of attempting player initialization
    if (isOffline) {
        AnimePosterLarge(
            url = posterUrl, contentDescription = contentDescription, modifier = modifier
        )
        return
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        contentAlignment = Alignment.Center
    ) {
        when (val currentState = state) {
            is TrailerPlayerState.Loading -> {
                CircularProgressIndicator()
            }

            is TrailerPlayerState.Restricted -> {
                TrailerFallbackUI(
                    posterUrl = posterUrl,
                    videoId = currentState.videoId,
                    siParameter = siParameter,
                    contentDescription = contentDescription,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is TrailerPlayerState.Error -> {
                TrailerFallbackUI(
                    posterUrl = posterUrl,
                    videoId = videoId,
                    siParameter = siParameter,
                    contentDescription = contentDescription,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is TrailerPlayerState.Playing -> {
                // Player is playing - view is already shown
            }
        }
        if (state !is TrailerPlayerState.Restricted && state !is TrailerPlayerState.Error) {
            AndroidView(factory = { ctx ->
                YouTubePlayerView(ctx).apply {
                    playerView = this
                    enableAutomaticInitialization = false
                    lifecycleOwner.lifecycle.addObserver(this)
                }
            }, modifier = Modifier.fillMaxWidth(), update = { view ->
                playerView = view
            }, onRelease = { view ->
                lifecycleOwner.lifecycle.removeObserver(view)
                view.release()
                playerView = null
            })

            LaunchedEffect(videoId, playerView) {
                val view = playerView ?: return@LaunchedEffect

                try {
                    view.initialize(
                        object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.cueVideo(videoId, 0f)
                                state = TrailerPlayerState.Playing
                            }

                            override fun onError(
                                youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError
                            ) {
                                when (error) {
                                    PlayerConstants.PlayerError.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER, PlayerConstants.PlayerError.UNKNOWN -> {
                                        state = TrailerPlayerState.Restricted(videoId)
                                        onPlayerError()
                                    }

                                    else -> {
                                        state = TrailerPlayerState.Error(error.name)
                                        onPlayerError()
                                    }
                                }
                            }
                        }, true, IFramePlayerOptions.Builder().build()
                    )
                } catch (e: Exception) {
                    state = TrailerPlayerState.Error(e.message ?: "Initialization failed")
                    onPlayerError()
                }
            }
        }

        DisposableEffect(videoId) {
            state = TrailerPlayerState.Loading
            onDispose {
                playerView?.release()
                playerView = null
            }
        }
    }
}

/**
 * Fallback UI shown when video cannot be embedded.
 *
 * @param posterUrl Poster image URL
 * @param videoId YouTube video ID
 * @param siParameter Optional session identifier parameter
 * @param contentDescription Content description for accessibility
 * @param modifier Modifier to be applied
 */
@Composable
private fun TrailerFallbackUI(
    posterUrl: String?,
    videoId: String,
    siParameter: String?,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
    ) {
        if (posterUrl != null) {
            AnimePosterLarge(
                url = posterUrl,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.3f), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.trailer_embedding_restricted),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.85f), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.trailer_embedding_restricted),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.trailer_embedding_restricted_reason),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Button(
                    onClick = {
                        val youtubeUrl = buildString {
                            append("https://www.youtube.com/watch?v=$videoId")
                            siParameter?.let { si ->
                                append("?si=$si")
                            }
                        }
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)).apply {
                            setPackage("com.google.android.youtube")
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }

                        try {
                            context.startActivity(intent)
                        } catch (_: ActivityNotFoundException) {
                            context.startActivity(
                                Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                            )
                        }
                    }) {
                    Text(
                        text = stringResource(R.string.trailer_watch_on_youtube),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}
