package com.example.game.data.remote

import com.example.game.data.remote.dto.GamesDto
import retrofit2.http.GET

interface GameApi {
    @GET("games")
    suspend fun getGame() : List<GamesDto>
}