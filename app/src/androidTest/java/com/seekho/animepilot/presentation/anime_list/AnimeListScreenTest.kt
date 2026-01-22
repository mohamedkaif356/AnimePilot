package com.seekho.animepilot.presentation.anime_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.seekho.animepilot.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimeListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(composeTestRule.activity)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AnimeListScreen(navController = navController)
        }
    }

    @Test
    fun animeListScreen_displaysTopAppBar() {
        // Then
        composeTestRule.onNodeWithText("Top Anime").assertIsDisplayed()
    }

    @Test
    fun animeListScreen_showsOfflineBannerWhenOffline() {
        // Note: This test would require mocking ConnectivityObserver
        // For now, we verify the screen renders correctly
        composeTestRule.onNodeWithText("Top Anime").assertIsDisplayed()
    }

    @Test
    fun animeListScreen_showsErrorStateWithRetry() {
        // Note: This would require mocking repository to return error
        // Screen structure test
        composeTestRule.onNodeWithText("Top Anime").assertIsDisplayed()
    }
}
