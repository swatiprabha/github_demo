package com.example.game.game_list

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import com.example.game.domain.repository.GamesRepository
import com.example.game.domain.usecase.GameUseCase
import com.example.game.core.common.Resource
import com.example.game.domain.model.Games
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalCoroutinesApi::class)
class GetGameUseCaseShould {

    private lateinit var gamesRepository: GamesRepository
    private lateinit var gameUseCase: GameUseCase
    private val gameList = mock<Flow<Resource<List<Games>>>>()

    @Before
    fun setUp() {
        gamesRepository = mock()
        gameUseCase = GameUseCase(gamesRepository)
    }

    @Test
    fun returnGameFromUseCaseInSuccess() = runTest {
        `when`(gamesRepository.getGames()).thenReturn(
            gameList
        )
        gameUseCase.invoke().onEach {
            Assert.assertEquals(gameList, it.data)
        }
    }

    @Test
    fun returnErrorFromUseCaseInSuccess() = runTest {
        `when`(gamesRepository.getGames()).thenThrow(
            RuntimeException("error")
        )
        gameUseCase.invoke().onEach {
            Assert.assertEquals("error", it.msg.toString())
        }

    }
}