package com.example.game.presentation.game.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.game.presentation.game.navigation.screens.Screen
import  com.example.game.presentation.game.state.UiEffect
import  com.example.game.presentation.game.viewmodel.GameViewModel
import com.example.game.presentation.game.components.GameScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavHostController) {

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState)}) {
        NavHost(navController = navController, startDestination = Screen.GameScreen.route, modifier = Modifier.padding(it)) {

            composable(Screen.GameScreen.route) {
                val gameViewModel = hiltViewModel<GameViewModel>()
                val state = gameViewModel.gameState.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = true) {
                    gameViewModel.uiEffect.collectLatest {uiEffect->
                        when (uiEffect) {
                            UiEffect.NavigateToDetailScreen -> {
                            }
                            is UiEffect.ShowSnackBar -> {
                                launch {
                                    snackBarHostState.showSnackbar(uiEffect.msg, duration = SnackbarDuration.Long)
                                }
                            }
                        }
                    }
                }
                GameScreen(gameViewModel, gameState = state.value, modifier = Modifier)
            }
        }
    }

}