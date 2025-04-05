package com.rocqjones.dailypulse.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rocqjones.dailypulse.android.enums.Screens
import com.rocqjones.dailypulse.android.screens.AboutScreen
import com.rocqjones.dailypulse.android.screens.ArticlesScreen
import com.rocqjones.dailypulse.android.screens.SourcesScreen
import com.rocqjones.dailypulse.articles.presentation.ArticlesViewModel

/**
 * Created by JonesMbindyo on 17/05/2024.
 * Copyright (c) 2024
 */
@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier.fillMaxSize().padding(it)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ARTICLES.route,
        modifier = modifier
    ) {
        composable(Screens.ARTICLES.route) {
            ArticlesScreen(
                onAboutButtonClick = { navController.navigate(Screens.ABOUT_DEVICE.route) },
                onSourcesButtonClick = { navController.navigate(Screens.SOURCES.route) }
            )
        }

        composable(Screens.SOURCES.route) {
            SourcesScreen(
                onUpButtonClick = { navController.popBackStack() }
            )
        }

        composable(Screens.ABOUT_DEVICE.route) {
            AboutScreen(
                onUpButtonClick = { navController.popBackStack() }
            )
        }
    }
}
