package com.example.movies.di

import android.content.Context
import androidx.room.Room
import com.example.offline.AppDatabase
import com.example.offline.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movies_database"
        ).build()
    }

    @Provides
    fun provideMovieDao(
        database: AppDatabase
    ): Dao {
        return database.movieDao()
    }
}