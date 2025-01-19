package com.example.designsystem.components.paging

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

enum class LocalPagingModeEnum {
    READY,
    LOADING,
    ERROR,
    EMPTY
}

val LocalPagingMode: ProvidableCompositionLocal<LocalPagingModeEnum?> = staticCompositionLocalOf { null }