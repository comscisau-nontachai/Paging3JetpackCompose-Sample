package com.example.paging3jetpackcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paging3jetpackcompose.data.UserDataSource
import com.example.paging3jetpackcompose.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo : UserRepository
) : ViewModel() {
    val userPager = Pager(
        PagingConfig(pageSize = 10)
    ){
        UserDataSource(repo)
    }.flow.cachedIn(viewModelScope)
}