package com.seekho.animepilot.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.seekho.animepilot.presentation.ui.component.OfflineBanner
import org.junit.Rule
import org.junit.Test

class OfflineBannerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun offlineBanner_displaysOfflineMessage() {
        // When
        composeTestRule.setContent {
            OfflineBanner()
        }

        // Then
        composeTestRule.onNodeWithText("Offline mode - Showing cached data").assertIsDisplayed()
    }

    @Test
    fun offlineBanner_iconHasContentDescription() {
        // When
        composeTestRule.setContent {
            OfflineBanner()
        }

        // Then
        composeTestRule.onNode(hasContentDescription("Offline")).assertIsDisplayed()
    }
}
