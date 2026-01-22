package com.seekho.animepilot.presentation.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.seekho.animepilot.R
import com.seekho.animepilot.ui.theme.Dimens

@Composable
fun RetryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val retryContentDesc = stringResource(R.string.content_desc_retry)
    Button(
        onClick = onClick,
        modifier = modifier.semantics {
            contentDescription = retryContentDesc
        }
    ) {
        Text(
            text = retryContentDesc,
            style = androidx.compose.material3.MaterialTheme.typography.labelLarge
        )
    }
}
