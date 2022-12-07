package com.example.paging3jetpackcompose.data.di

import com.example.paging3jetpackcompose.data.network.UserApi
import com.example.paging3jetpackcompose.data.repository.UserRepository
import com.example.paging3jetpackcompose.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideUserApi() : UserApi = UserApi()

    @Provides
    fun provideUserRepository(api : UserApi) : UserRepository = UserRepositoryImpl(api)
}