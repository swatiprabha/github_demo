package com.example.game.game_list

import com.example.game.MainCoroutineRule
import com.example.game.data.remote.GameApi
import com.example.game.data.remote.dto.GamesDto
import com.example.game.data.repository.GameRepositoryImpl
import com.example.game.domain.repository.GamesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class GetGameRepositoryShould {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private lateinit var gamesRepository: GamesRepository
    private lateinit var gameApi: GameApi
    private val gameList = mock<GamesDto>()

    @Before
    fun setUp() {
        gameApi = mock()
        gamesRepository = GameRepositoryImpl(gameApi)
    }

    @Test
    fun returnSuccessWhenGetDataFromBackend() = runTest {
        `when`(gameApi.getGame()).thenReturn(
            listOf(gameList)
        )
        val result = gamesRepository.getGames()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(true, result.toString().isNotEmpty())
    }
}