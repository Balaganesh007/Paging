package com.example.paging.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging.model.Result
import com.example.paging.network.ObjectRickAndMortyApi
import com.example.paging.network.RickAndMortyApi


class Paging(private val apiCall: ObjectRickAndMortyApi) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiCall.retrofitService.getProperties(currentLoadingPageKey)
            val responseData = mutableListOf<Result>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
