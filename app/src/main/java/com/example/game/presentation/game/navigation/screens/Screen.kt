package com.example.game.presentation.game.navigation.screens

sealed class Screen(val route: String) {
    object GameScreen : Screen("game_screen")
    object GameDetailScreen : Screen("game_detail_screen")
}
