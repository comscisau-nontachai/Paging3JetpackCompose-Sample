package com.example.paging3jetpackcompose.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3jetpackcompose.data.network.User
import com.example.paging3jetpackcompose.data.repository.UserRepository

class UserDataSource(
    private val repo : UserRepository
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { pos ->
            val page = state.closestPageToPosition(pos)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val response = repo.getUser(page,10)
            LoadResult.Page(
                data = response.users,
                prevKey = null,
                nextKey = if(response.users.isNotEmpty()) response.page.plus(1) else null
            )

        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }

}