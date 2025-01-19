package com.example.common.basedao

import androidx.room.Delete
import androidx.room.Upsert

interface BaseDao<T> {
    @Upsert
    suspend fun upsert(obj: T)

    @Delete
    suspend fun delete(obj: T)

    @Delete
    suspend fun delete(obj: List<T>)
}