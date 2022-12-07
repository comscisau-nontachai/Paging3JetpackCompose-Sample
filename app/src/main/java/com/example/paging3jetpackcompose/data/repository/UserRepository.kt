package com.example.paging3jetpackcompose.data.repository

import com.example.paging3jetpackcompose.data.network.UserResponse

interface UserRepository {
    suspend fun getUser(page : Int, limit : Int) : UserResponse
}