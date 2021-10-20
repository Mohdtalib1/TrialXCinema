package com.mbytessolution.trialxcinema.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("SELECT * From MoviesList ORDER BY id DESC")
    fun getAllMovies(): List<MovieEntity>?

    @Insert
    fun insertFavouriteMovies(movie: MovieEntity?)

    @Query("SELECT EXISTS(SELECT * FROM MoviesList where first_banner=:url)")
    fun hasItem(url: String): Boolean

    @Query("DELETE FROM MoviesList where first_banner=:url")
    fun deleteFrom(url: String)

}