package com.promovatestproject.database.di

import android.content.Context
import androidx.room.Room
import com.promovatestproject.database.PromovaTestDatabase
import com.promovatestproject.database.di.constants.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseHiltModule {
    @Provides
    @Singleton
    fun providePromovaTestDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        PromovaTestDatabase::class.java,
        DatabaseConstants.DATABASE_NAME
    ).build()
}