package com.example.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTv(
        tv: List<TvEntity>
    )

    @Query("""
        SELECT * FROM tv
        WHERE page = :page
        AND genreId = :genreId
    """)
    suspend fun getTv(
        page: Int,
        genreId: Int
    ): List<TvEntity>
    @Query("SELECT * FROM tv WHERE id =  :id  ")
    suspend fun getMovie(id : Int) : TvEntity
}