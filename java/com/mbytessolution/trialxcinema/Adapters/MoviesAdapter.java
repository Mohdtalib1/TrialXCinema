package com.mbytessolution.trialxcinema.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mbytessolution.trialxcinema.Model.Movies;
import com.mbytessolution.trialxcinema.view.Activity.MovieDetailsActivity;
import com.mbytessolution.trialxcinema.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private Context mContext;
    private ArrayList<Movies> moviesArrayList;

    public MoviesAdapter(Context mContext, ArrayList<Movies> moviesArrayList) {
        this.mContext = mContext;
        this.moviesArrayList = moviesArrayList;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_movie, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {

        Movies movies = moviesArrayList.get(position);
        String base_image_url = "http://image.tmdb.org/t/p/w780"+movies.getFirst_banner();

        Picasso.get().load(base_image_url)
                .placeholder(R.drawable.placeholder_loading).error(R.drawable.placeholder_loading)
                .into(holder.banner);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("second_banner", movies.getSecond_banner());
                intent.putExtra("first_banner", movies.getFirst_banner());
                intent.putExtra("movie_title", movies.getTitle());
                intent.putExtra("rating", movies.getRating());
                intent.putExtra("date", movies.getRelease_date());
                intent.putExtra("language", movies.getOriginal_language());
                intent.putExtra("Overview", movies.getOverview());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        private ImageView banner;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            banner = itemView.findViewById(R.id.movie_banner);
        }
    }

}
