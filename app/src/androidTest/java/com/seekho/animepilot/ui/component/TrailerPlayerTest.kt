package com.seekho.animepilot.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.seekho.animepilot.presentation.ui.component.TrailerPlayer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TrailerPlayerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun trailerPlayer_showsPosterWhenNoTrailerUrl() {
        // When
        composeTestRule.setContent {
            TrailerPlayer(
                trailerUrl = null,
                posterUrl = "https://example.com/poster.jpg",
                contentDescription = "Test Anime"
            )
        }

        // Then - Should show poster (fallback)
        composeTestRule.onNodeWithContentDescription("Test Anime").assertIsDisplayed()
    }

    @Test
    fun trailerPlayer_showsPosterWhenInvalidTrailerUrl() {
        // When
        composeTestRule.setContent {
            TrailerPlayer(
                trailerUrl = "https://example.com/not-youtube",
                posterUrl = "https://example.com/poster.jpg",
                contentDescription = "Test Anime"
            )
        }

        // Then - Should show poster (fallback due to invalid URL)
        composeTestRule.onNodeWithContentDescription("Test Anime").assertIsDisplayed()
    }
}
