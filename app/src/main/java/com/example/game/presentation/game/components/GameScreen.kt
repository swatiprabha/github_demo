package com.example.game.presentation.game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.game.presentation.game.state.GameState
import com.example.game.presentation.game.viewmodel.GameViewModel

lateinit var mGameState: GameState
lateinit var mmodifier: Modifier
lateinit var mviewModel: GameViewModel

@Composable
fun GameScreen(viewModel: GameViewModel,
               gameState: GameState, modifier: Modifier) {
    mGameState = gameState
    mmodifier = modifier
    mviewModel = viewModel

    when {
        gameState.games?.isNotEmpty()!! -> {
            LazyColumn(modifier = Modifier.testTag("game_list")) {
                items(gameState.games) {
                    viewModel.setGame(it)
                    GameItem(modifier, it)
                }
            }
        }
        gameState.isLoading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = modifier
                        .align(Alignment.Center)
                        .testTag("progress")
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GameScreen(viewModel = mviewModel,  gameState = mGameState , modifier = mmodifier )
}
