package com.seekho.animepilot.ui.component

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.seekho.animepilot.core.domain.model.Anime
import com.seekho.animepilot.presentation.ui.component.AnimeCard
import org.junit.Rule
import org.junit.Test

class AnimeCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun animeCard_displaysTitle() {
        // Given
        val anime = Anime(
            id = 1,
            title = "Test Anime",
            posterUrl = "https://example.com/image.jpg",
            episodes = 12,
            rating = 8.5
        )

        // When
        composeTestRule.setContent {
            AnimeCard(
                anime = anime,
                onClick = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Test Anime").assertIsDisplayed()
    }

    @Test
    fun animeCard_displaysRating() {
        // Given
        val anime = Anime(
            id = 1,
            title = "Test Anime",
            posterUrl = "https://example.com/image.jpg",
            episodes = null,
            rating = 8.5
        )

        // When
        composeTestRule.setContent {
            AnimeCard(
                anime = anime,
                onClick = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Rating: 8.5").assertIsDisplayed()
    }

    @Test
    fun animeCard_displaysEpisodes() {
        // Given
        val anime = Anime(
            id = 1,
            title = "Test Anime",
            posterUrl = "https://example.com/image.jpg",
            episodes = 12,
            rating = null
        )

        // When
        composeTestRule.setContent {
            AnimeCard(
                anime = anime,
                onClick = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Episodes: 12").assertIsDisplayed()
    }

    @Test
    fun animeCard_hasClickAction() {
        // Given
        val anime = Anime(
            id = 1,
            title = "Test Anime",
            posterUrl = "https://example.com/image.jpg",
            episodes = null,
            rating = null
        )

        // When
        composeTestRule.setContent {
            AnimeCard(
                anime = anime,
                onClick = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Test Anime").assertHasClickAction()
    }

    @Test
    fun animeCard_posterHasContentDescription() {
        // Given
        val anime = Anime(
            id = 1,
            title = "Test Anime",
            posterUrl = "https://example.com/image.jpg",
            episodes = null,
            rating = null
        )

        // When
        composeTestRule.setContent {
            AnimeCard(
                anime = anime,
                onClick = {}
            )
        }

        // Then
        composeTestRule.onNode(hasContentDescription("Test Anime")).assertIsDisplayed()
    }

    @Test
    fun animeCard_showsOfflineBadgeWhenOffline() {
        // Given
        val anime = Anime(
            id = 1,
            title = "Test Anime",
            posterUrl = "https://example.com/image.jpg",
            episodes = null,
            rating = null
        )

        // When
        composeTestRule.setContent {
            AnimeCard(
                anime = anime,
                onClick = {},
                isOffline = true
            )
        }

        // Then - offline badge should be visible (check for icon with content description)
        composeTestRule.onNode(hasContentDescription("Offline")).assertIsDisplayed()
    }
}
