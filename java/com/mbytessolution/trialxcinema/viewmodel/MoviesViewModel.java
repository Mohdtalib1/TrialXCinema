package com.mbytessolution.trialxcinema.viewmodel;

import com.mbytessolution.trialxcinema.Model.Movies;
import com.mbytessolution.trialxcinema.repository.MoviesRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoviesViewModel extends ViewModel {

    private MoviesRepository moviesRepository;
    private MutableLiveData<List<Movies>> mutableLiveData;

    public MoviesViewModel() {
        this.moviesRepository = new MoviesRepository();
    }

    public LiveData<List<Movies>> getMovies() {
        if (mutableLiveData == null) {
            mutableLiveData = moviesRepository.requestMovies();
        }

        return mutableLiveData;
    }

}
