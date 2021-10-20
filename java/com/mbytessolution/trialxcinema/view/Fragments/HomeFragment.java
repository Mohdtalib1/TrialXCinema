package com.mbytessolution.trialxcinema.view.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.mbytessolution.trialxcinema.Adapters.MoviesAdapter;
import com.mbytessolution.trialxcinema.Model.Movies;
import com.mbytessolution.trialxcinema.Model.MoviesResponse;
import com.mbytessolution.trialxcinema.R;
import com.mbytessolution.trialxcinema.Retrofit.GetMovies;
import com.mbytessolution.trialxcinema.Retrofit.RetrofitClientInstance;
import com.mbytessolution.trialxcinema.viewmodel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private View mView;
    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private ArrayList<Movies> moviesArrayList;
    private ProgressBar progress_bar;
    private ConstraintLayout rel1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home, container, false);

        initializeViews(mView);

        return mView;
    }

    private void initializeViews(View view) {


        progress_bar = (ProgressBar) view.findViewById(R.id.progress_bar);
        rel1 = (ConstraintLayout) view.findViewById(R.id.rel1);


        moviesRecyclerView = (RecyclerView) view.findViewById(R.id.movies_recycler_view);
        moviesArrayList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        moviesRecyclerView.setLayoutManager(layoutManager);
        moviesRecyclerView.setNestedScrollingEnabled(false);
        moviesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        moviesRecyclerView.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter(getContext(), moviesArrayList);
        moviesRecyclerView.setAdapter(moviesAdapter);

        MoviesViewModel viewModel = new MoviesViewModel();

        if (!checkInternetConnection()) {
            if (progress_bar.getVisibility() == View.VISIBLE) {
                progress_bar.setVisibility(View.GONE);
            }
            Toast.makeText(getContext(), "No Internet Connection..", Toast.LENGTH_SHORT).show();

        }
        else {
            viewModel.getMovies().observe(getActivity(), new Observer<List<Movies>>() {
                @Override
                public void onChanged(List<Movies> movies) {
                    if (movies != null && !movies.isEmpty()) {
                        moviesArrayList.addAll(movies);
                        moviesAdapter.notifyDataSetChanged();
                        if (progress_bar.getVisibility() == View.VISIBLE) {
                            progress_bar.setVisibility(View.GONE);
                        }
                    }
                }
            });
        }



    }

    private boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                        .getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        else {
            return false;
        }
    }

}