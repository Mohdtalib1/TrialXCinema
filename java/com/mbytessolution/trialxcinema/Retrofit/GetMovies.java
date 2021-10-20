package com.mbytessolution.trialxcinema.Retrofit;

import com.mbytessolution.trialxcinema.Model.Movies;
import com.mbytessolution.trialxcinema.Model.MoviesResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMovies {

    @GET("popular?")
    Call<MoviesResponse> getAllMovies(@Query("api_key") String apiKey);

}
