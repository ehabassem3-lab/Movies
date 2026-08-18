package com.example.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.network.response.cast.Actor
import com.example.movies.network.response.cast.ActorWork
import com.example.movies.network.response.cast.CastItem


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
    suspend fun getTv(id : Int) : TvEntity



}