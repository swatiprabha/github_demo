package com.example.game.domain.repository

import com.example.game.core.common.Resource
import com.example.game.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getGames() : Flow<Resource<List<Games>>>
}