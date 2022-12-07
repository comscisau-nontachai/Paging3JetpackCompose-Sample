package com.example.paging3jetpackcompose.data.repository

import com.example.paging3jetpackcompose.data.network.UserApi
import com.example.paging3jetpackcompose.data.network.UserResponse
import kotlinx.coroutines.delay

class UserRepositoryImpl(
    private val api : UserApi
) : UserRepository {
    override suspend fun getUser(page: Int, limit: Int): UserResponse {
        delay(3000L)
        return api.getUsers(page,limit)
    }
}