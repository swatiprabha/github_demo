package com.example.game.presentation.game.state

import com.example.game.domain.model.Games

data class GameState(
 val games: List<Games>? = emptyList(),
 val errorMsg: String? = "",
 val isLoading: Boolean = false
)