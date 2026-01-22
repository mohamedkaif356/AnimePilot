package com.seekho.animepilot.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.seekho.animepilot.ui.theme.Dimens
import com.seekho.animepilot.ui.theme.Elevation
import com.seekho.animepilot.ui.theme.ShapeTokens

@Composable
fun AnimeCardShimmer(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.cardDefault),
        shape = ShapeTokens.cardShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.cardContentPadding),
            horizontalArrangement = Arrangement.spacedBy(Dimens.spaceM)
        ) {
            ShimmerBox(
                modifier = Modifier
                    .width(Dimens.thumbnailWidth)
                    .height(Dimens.thumbnailHeight)
                    .clip(ShapeTokens.imageShape)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Dimens.spaceXS)
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.spaceXL)
                        .clip(ShapeTokens.chipShape)
                )
                ShimmerBox(
                    modifier = Modifier
                        .width(Dimens.thumbnailWidth)
                        .height(Dimens.spaceL)
                        .clip(ShapeTokens.chipShape)
                )
                ShimmerBox(
                    modifier = Modifier
                        .width(Dimens.thumbnailWidth)
                        .height(Dimens.spaceL)
                        .clip(ShapeTokens.chipShape)
                )
            }
        }
    }
}

@Composable
fun AnimeDetailShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.cardContentPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceL)
    ) {
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.posterHeight)
                .clip(ShapeTokens.imageShape)
        )

        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(Dimens.spaceXL + Dimens.spaceS)
                .clip(ShapeTokens.chipShape)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.spaceL)
        ) {
            ShimmerBox(
                modifier = Modifier
                    .width(Dimens.thumbnailWidth + Dimens.spaceXL)
                    .height(Dimens.spaceXL)
                    .clip(ShapeTokens.chipShape)
            )
            ShimmerBox(
                modifier = Modifier
                    .width(Dimens.thumbnailWidth + Dimens.spaceXL)
                    .height(Dimens.spaceXL)
                    .clip(ShapeTokens.chipShape)
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.cardDefault),
            shape = ShapeTokens.cardShape
        ) {
            Column(
                modifier = Modifier.padding(Dimens.cardContentPadding),
                verticalArrangement = Arrangement.spacedBy(Dimens.spaceS)
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .width(Dimens.thumbnailWidth + Dimens.spaceS)
                        .height(Dimens.spaceXL)
                        .clip(ShapeTokens.chipShape)
                )
                repeat(4) {
                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Dimens.spaceL)
                            .clip(ShapeTokens.chipShape)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeListShimmer(
    itemCount: Int = 5,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(Dimens.spaceL),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceM)
    ) {
        items(itemCount) {
            AnimeCardShimmer()
        }
    }
}

@Composable
private fun ShimmerBox(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerTranslateAnim = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing
            )
        ),
        label = "shimmer_translate"
    )
    
    val shimmerColors = listOf(
        Color(0xFFE0E0E0).copy(alpha = 0.6f),
        Color(0xFFF5F5F5).copy(alpha = 0.8f),
        Color(0xFFE0E0E0).copy(alpha = 0.6f)
    )
    
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(shimmerTranslateAnim.value - 300f, shimmerTranslateAnim.value - 300f),
                    end = Offset(shimmerTranslateAnim.value, shimmerTranslateAnim.value)
                )
            )
    )
}
