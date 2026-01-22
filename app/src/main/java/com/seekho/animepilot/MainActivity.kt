package com.seekho.animepilot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.seekho.animepilot.presentation.anime_detail.AnimeDetailScreen
import com.seekho.animepilot.presentation.anime_list.AnimeListScreen
import com.seekho.animepilot.presentation.navigation.Screen
import com.seekho.animepilot.ui.theme.AnimePilotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimePilotTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.ANIME_LIST,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(route = Screen.ANIME_LIST, enterTransition = {
                        fadeIn(tween(300)) + scaleIn(
                            initialScale = 0.95f,
                            animationSpec = tween(300)
                        )
                    }, exitTransition = {
                        fadeOut(tween(300)) + scaleOut(
                            targetScale = 0.98f,
                            animationSpec = tween(300)
                        )
                    }) {
                        AnimeListScreen(navController = navController)
                    }

                    composable(
                        route = Screen.ANIME_DETAIL, arguments = listOf(
                        navArgument("animeId") {
                            type = NavType.IntType
                        }), enterTransition = {
                        fadeIn(tween(300)) + scaleIn(
                            initialScale = 0.95f,
                            animationSpec = tween(300)
                        )
                    }, exitTransition = {
                        fadeOut(tween(300)) + scaleOut(
                            targetScale = 0.98f,
                            animationSpec = tween(300)
                        )
                    }) { backStackEntry ->
                        val animeId = backStackEntry.arguments?.getInt("animeId")
                        if (animeId != null && animeId > 0) {
                            AnimeDetailScreen(
                                animeId = animeId, navController = navController
                            )
                        } else {
                            androidx.compose.runtime.LaunchedEffect(Unit) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}