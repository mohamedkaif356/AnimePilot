package com.seekho.animepilot.ui.component

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.seekho.animepilot.presentation.ui.component.ErrorState
import org.junit.Rule
import org.junit.Test

class ErrorStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorState_displaysTitle() {
        // When
        composeTestRule.setContent {
            ErrorState(
                title = "Error Title",
                message = "Error message",
                canRetry = true,
                onRetry = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Error Title").assertIsDisplayed()
    }

    @Test
    fun errorState_displaysMessage() {
        // When
        composeTestRule.setContent {
            ErrorState(
                title = "Error Title",
                message = "Error message",
                canRetry = true,
                onRetry = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Error message").assertIsDisplayed()
    }

    @Test
    fun errorState_showsRetryButtonWhenCanRetry() {
        // When
        composeTestRule.setContent {
            ErrorState(
                title = "Error Title",
                message = "Error message",
                canRetry = true,
                onRetry = {}
            )
        }

        // Then
        composeTestRule.onNode(hasContentDescription("Retry")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("Retry")).assertHasClickAction()
    }

    @Test
    fun errorState_hidesRetryButtonWhenCannotRetry() {
        // When
        composeTestRule.setContent {
            ErrorState(
                title = "Error Title",
                message = "Error message",
                canRetry = false,
                onRetry = {}
            )
        }

        // Then - retry button should not be displayed
        composeTestRule.onNode(hasContentDescription("Retry")).assertDoesNotExist()
    }
}
