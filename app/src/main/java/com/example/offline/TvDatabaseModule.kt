package com.example.offline

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


    @Module
    @InstallIn(SingletonComponent::class)
    object TvDatabaseModule {

        @Provides
        @Singleton
        fun provideTvDatabase(
            @ApplicationContext context: Context
        ): TvDatabase {
            return Room.databaseBuilder(
                context,
                TvDatabase::class.java,
                "tv_database"
            ).build()
        }

        @Provides
        fun provideTvDao(
            database: TvDatabase
        ): TvDao {
            return database.tvDao()
        }
    }
