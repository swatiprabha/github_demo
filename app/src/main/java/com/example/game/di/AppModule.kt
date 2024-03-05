package com.example.game.di

import com.example.game.core.utils.Constants.BASE_URL
import com.example.game.data.remote.GameApi
import com.example.game.data.repository.GameRepositoryImpl
import com.example.game.domain.repository.GamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideGameApi(retrofit: Retrofit) : GameApi = retrofit.create(GameApi::class.java)

    @Provides
    @Singleton
    fun provideGameRepository(gameApi: GameApi) : GamesRepository {
        return GameRepositoryImpl(gameApi)
    }
}