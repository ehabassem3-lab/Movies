package com.example.movies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TvDao {

//    @Query("SELECT * FROM tv WHERE genreId = :genreId")
//    suspend fun getTvByGenre(genreId: Int?): List<DataBase>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(tv: List<DataBase>)
}