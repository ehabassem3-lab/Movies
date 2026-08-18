package com.example.offline

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object Castmodule {
//
//    @Provides
//    @Singleton
//    fun provideCastDatabase(
//        @ApplicationContext context: Context
//    ): CastDataBase {
//        return Room.databaseBuilder(
//            context,
//            CastDataBase::class.java,
//            "cast_database"
//        ).build()
//    }
//
//    @Provides
//    fun provideCastDao(
//        database: CastDataBase
//    ): CastDao {
//        return database.cast()
//    }
//}