package com.mbytessolution.trialxcinema.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbytessolution.trialxcinema.db.MovieEntity
import com.mbytessolution.trialxcinema.db.MoviesDao
import com.mbytessolution.trialxcinema.db.RoomAppDB

class FavouriteMoviesViewModel(app: Application): AndroidViewModel(app) {

     var allMovies: MutableLiveData<List<MovieEntity>>
    private var isAdded: Boolean
    private var moviesDao: MoviesDao;


    init {
        allMovies = MutableLiveData();
        isAdded = true;
        moviesDao = RoomAppDB.getAppDatabase((getApplication()))?.moviesDao()!!
    }

    fun getAllUserObservers(): MutableLiveData<List<MovieEntity>> {
        if (moviesDao == null) {
            moviesDao = RoomAppDB.getAppDatabase((getApplication()))?.moviesDao()!!
        }

        var list = moviesDao.getAllMovies()
        allMovies.postValue(list)

        return allMovies
    }

    fun isAddedToFavourite(url: String): Boolean {
        if (moviesDao == null) {
            moviesDao = RoomAppDB.getAppDatabase((getApplication()))?.moviesDao()!!
        }
        isAdded = (moviesDao.hasItem(url))

        return isAdded;
    }


    fun deleteFromFavourite(url: String) {
        if (moviesDao == null) {
            moviesDao = RoomAppDB.getAppDatabase((getApplication()))?.moviesDao()!!
        }
        moviesDao.deleteFrom(url)

    }


    fun insertFavouriteMovies(entity: MovieEntity){
      val movieDao =  RoomAppDB.getAppDatabase(getApplication())?.moviesDao()
        movieDao?.insertFavouriteMovies(entity);
    }
}