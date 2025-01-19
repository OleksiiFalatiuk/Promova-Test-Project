package com.example.common.filehelper

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object FilesModule {
    @FilesDir
    @Provides
    fun providesFilesDir(@ApplicationContext context: Context): File = context.filesDir
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FilesDir