package com.mbytessolution.trialxcinema.repository;

import android.view.View;
import android.widget.Toast;

import com.mbytessolution.trialxcinema.Model.Movies;
import com.mbytessolution.trialxcinema.Model.MoviesResponse;
import com.mbytessolution.trialxcinema.Retrofit.GetMovies;
import com.mbytessolution.trialxcinema.Retrofit.RetrofitClientInstance;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    public MutableLiveData<List<Movies>> requestMovies() {
        final MutableLiveData<List<Movies>> mutableLiveData = new MutableLiveData<>();

        try {
            GetMovies apiInterface = RetrofitClientInstance.getClient().create(GetMovies.class);
            Call<MoviesResponse> call = apiInterface.getAllMovies("90787843a200cfbfd55b14b39270f6a1");
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    try {
                        if (response.body() != null) {
                           mutableLiveData.setValue(response.body().getMoviesArrayList());

                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    t.printStackTrace();


                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }

        return mutableLiveData;

    }
}
