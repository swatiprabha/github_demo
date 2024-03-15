package com.example.game.game_list

import com.example.game.MainCoroutineRule
import com.example.game.core.common.Resource
import com.example.game.domain.model.Games
import com.example.game.domain.usecase.GameUseCase
import com.example.game.presentation.game.viewmodel.GameViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelShould {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private val gameUseCase: GameUseCase = mock()
    private lateinit var gameViewModel: GameViewModel
    private val gameList: List<Games>? = mock()

    @Before
    fun setup() {
        gameViewModel = GameViewModel(gameUseCase)
    }

    @Test
    fun validateUserWillSeeProgressBarInitially() = runTest {
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()

        `when`(gameUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Loading())
            }
        )
        Assert.assertEquals(true,gameViewModel.gameState.value.isLoading)
    }

    @Test
    fun validateSuccessStateIsStoredInMovieList() = runTest {
        `when`(gameUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Success(gameList))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(gameList,gameViewModel.gameState.value.games)
    }

    @Test
    fun throwErrorInErrorCase() = runTest {
        `when`(gameUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Error("Something Went Wrong"))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals("Something Went Wrong",gameViewModel.gameState.value.errorMsg)
    }
}

