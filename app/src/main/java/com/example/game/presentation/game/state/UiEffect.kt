package com.example.game.presentation.game.state

sealed class UiEffect {
    class ShowSnackBar(val msg: String) : UiEffect()
    object NavigateToDetailScreen : UiEffect()
}