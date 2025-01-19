package com.promovatestproject.testing.utils.creators

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface MultipleModelsCreator<T : Any> : SingleModelCreator<T> {
    override val model: T

    fun list(count: Int = 2): List<T>

    fun paging(count: Int = 2): Flow<PagingData<T>> = flowOf(
        PagingData.from(
            list(count = count),
            sourceLoadStates = LoadStates(
                refresh = LoadState.NotLoading(endOfPaginationReached = true),
                append = LoadState.NotLoading(endOfPaginationReached = true),
                prepend = LoadState.NotLoading(endOfPaginationReached = true)
            )
        )
    )
}