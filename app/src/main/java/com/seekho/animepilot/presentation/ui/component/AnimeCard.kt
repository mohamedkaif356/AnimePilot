package com.seekho.animepilot.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WifiOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.seekho.animepilot.R
import com.seekho.animepilot.core.domain.model.Anime
import com.seekho.animepilot.ui.theme.Dimens
import com.seekho.animepilot.ui.theme.Elevation
import com.seekho.animepilot.ui.theme.ShapeTokens


@Composable
fun AnimeCard(
    anime: Anime,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isOffline: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                role = Role.Button,
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isPressed) Elevation.elevationL else Elevation.cardDefault
        ),
        shape = ShapeTokens.cardShape
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.cardContentPadding),
                horizontalArrangement = Arrangement.spacedBy(Dimens.spaceM)
            ) {
                AnimePosterThumbnail(
                    url = anime.posterUrl,
                    contentDescription = anime.title
                )
                
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(Dimens.spaceXS)
                ) {
                    Text(
                        text = anime.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    
                    if (anime.rating != null) {
                        Text(
                            text = stringResource(R.string.anime_rating, anime.rating.toString()),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    
                    anime.episodes?.let { episodes ->
                        Text(
                            text = stringResource(R.string.anime_episodes, episodes),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            if (isOffline) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = -Dimens.spaceXS, y = Dimens.spaceXS),
                    shape = ShapeTokens.chipShape,
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Icon(
                        imageVector = Icons.Outlined.WifiOff,
                        contentDescription = stringResource(R.string.content_desc_offline),
                        modifier = Modifier
                            .padding(Dimens.spaceXS)
                            .size(Dimens.iconSmall),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
