package com.example.game.data.repository

import com.example.game.core.common.Resource
import com.example.game.data.remote.GameApi
import com.example.game.data.remote.mapper.toDomainGames
import com.example.game.domain.model.Games
import com.example.game.domain.repository.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val gameApi: GameApi) :
    GamesRepository {

    override fun getGames(): Flow<Resource<List<Games>>> = flow {
        emit(Resource.Loading())
        val result = gameApi.getGame().map {
            it.toDomainGames()
        }
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

}