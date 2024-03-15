package com.example.game.presentation.game.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.game.presentation.game.state.GameState
import com.example.game.presentation.game.state.UiEffect
import com.games.freegameapp.presentation.free_game.state.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.game.core.common.Resource
import com.example.game.domain.model.Games
import com.example.game.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class GameViewModel @Inject constructor(private val useCase: GameUseCase) : ViewModel() {

    private val _gameState = MutableStateFlow(GameState())

    val gameState: StateFlow<GameState>
        get() = _gameState

    private val _uiEffect  = MutableSharedFlow<UiEffect>()

    val uiEffect: SharedFlow<UiEffect>
        get() = _uiEffect.asSharedFlow()

    private val _gameDetails: MutableState<Games> = mutableStateOf(Games())
    val gameDetails: MutableState<Games> = _gameDetails

    fun setGame(data:  Games){
        _gameDetails.value = data
    }

    init {
        getAllFreeGames()
    }

    private fun getAllFreeGames() =
        useCase().onEach {
            when (it) {
                is Resource.Error -> {
                    _gameState.value = GameState().copy(errorMsg = it.msg)
                    _uiEffect.emit(UiEffect.ShowSnackBar(it.msg.toString()))
                }

                is Resource.Loading -> {
                    _gameState.value = GameState().copy(isLoading = true)
                }

                is Resource.Success -> {
                    _gameState.value = GameState().copy(games = it.data)
                }
            }
        }.launchIn(viewModelScope)

    fun onEvent(uiEvent: UiEvent) {
        when(uiEvent) {
            UiEvent.NavigateToDetailScreen -> {

            }
        }
    }
}