package com.example.game.data.remote.mapper

import com.example.game.data.remote.dto.GamesDto
import com.example.game.domain.model.Games

fun GamesDto.toDomainGames() : Games {
    return Games(gameUrl, id, shortDescription, thumbnail, title)
}


