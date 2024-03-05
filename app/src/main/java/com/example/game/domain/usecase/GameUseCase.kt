package com.example.game.domain.usecase

import com.example.game.domain.repository.GamesRepository
import javax.inject.Inject

class GameUseCase @Inject constructor(private val repository: GamesRepository) {

    operator fun invoke() = repository.getGames()
}