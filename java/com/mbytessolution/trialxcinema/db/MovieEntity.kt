package com.mbytessolution.trialxcinema.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MoviesList")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "second_banner")
    val secondBanner: String,
    @ColumnInfo(name = "first_banner")
    val firstBanner: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String
)