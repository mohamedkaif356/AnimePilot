package com.seekho.animepilot.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

val AnimePilotShapes = Shapes(
    extraSmall = RoundedCornerShape(Dimens.radiusSmall),
    small = RoundedCornerShape(Dimens.radiusSmall),
    medium = RoundedCornerShape(Dimens.radiusMedium),
    large = RoundedCornerShape(Dimens.radiusLarge),
    extraLarge = RoundedCornerShape(Dimens.radiusXL)
)

object ShapeTokens {
    val cardShape = AnimePilotShapes.medium
    val chipShape = AnimePilotShapes.small
    val imageShape = AnimePilotShapes.medium
}
