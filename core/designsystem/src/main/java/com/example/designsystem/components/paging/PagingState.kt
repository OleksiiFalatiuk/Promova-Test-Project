package com.example.designsystem.components.paging

import androidx.paging.compose.LazyPagingItems

data class PagingState<T : Any>(
    val pagingItems: LazyPagingItems<T>,
    val isLoading: Boolean,
    val isAppendLoading: Boolean,
    val isLoadingError: Boolean,
    val isAppendError: Boolean,
    val isNoItems: Boolean
)