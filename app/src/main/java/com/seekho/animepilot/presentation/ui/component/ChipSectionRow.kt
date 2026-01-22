package com.seekho.animepilot.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.seekho.animepilot.ui.theme.Dimens
import com.seekho.animepilot.ui.theme.Elevation
import com.seekho.animepilot.ui.theme.ShapeTokens

@Composable
fun ChipSectionRow(
    title: String,
    items: List<String>,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) return
    
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.cardDefault),
        shape = ShapeTokens.cardShape
    ) {
        Column(
            modifier = Modifier.padding(Dimens.cardContentPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceS)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Dimens.spaceS),
                contentPadding = PaddingValues(vertical = Dimens.spaceXS)
            ) {
                items(items, key = { it }) { item ->
                    FilterChip(
                        selected = false,
                        onClick = { },
                        label = { 
                            Text(
                                text = item,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                        shape = ShapeTokens.chipShape
                    )
                }
            }
        }
    }
}
