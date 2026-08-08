package com.example.offline

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "item")
data class ItemEntity (
    @PrimaryKey
    val id: Int,
    val type : String,
    val title: String,
    val posterPath: String?,
    val rating: Double
)
