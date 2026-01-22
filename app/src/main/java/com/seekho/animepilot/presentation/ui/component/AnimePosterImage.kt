package com.seekho.animepilot.presentation.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.seekho.animepilot.R
import com.seekho.animepilot.ui.theme.Dimens
import com.seekho.animepilot.ui.theme.ShapeTokens

@Composable
fun AnimePosterImage(
    url: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    sizeModifier: Modifier? = null
) {
    if (url == null) return
    
    val imageModifier = sizeModifier?.then(modifier) ?: modifier
    
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription ?: stringResource(R.string.content_desc_anime_poster),
        modifier = imageModifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun AnimePosterThumbnail(
    url: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    AnimePosterImage(
        url = url,
        modifier = modifier
            .clip(ShapeTokens.imageShape)
            .width(Dimens.thumbnailWidth)
            .aspectRatio(2f / 3f),
        contentDescription = contentDescription
    )
}

@Composable
fun AnimePosterLarge(
    url: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    AnimePosterImage(
        url = url,
        modifier = modifier
            .clip(ShapeTokens.imageShape)
            .fillMaxWidth()
            .aspectRatio(2f / 3f),
        contentDescription = contentDescription
    )
}
