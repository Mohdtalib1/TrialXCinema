package com.mbytessolution.trialxcinema.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbytessolution.trialxcinema.R
import com.mbytessolution.trialxcinema.db.MovieEntity
import com.mbytessolution.trialxcinema.view.Activity.MovieDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_movie.view.*

class FavouriteMoviesAdapter(val context: Context): RecyclerView.Adapter<FavouriteMoviesAdapter.FavouriteViewHolder>() {

    private var moviesList = ArrayList<MovieEntity>()

    fun setListData(moviesList: ArrayList<MovieEntity>) {
        this.moviesList = moviesList

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
      var inflater = LayoutInflater.from(parent.context).inflate(R.layout.single_movie, parent, false)
        return FavouriteViewHolder(inflater)
    }

    override fun getItemCount(): Int {
       return moviesList.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(moviesList[position], context)

    }

    class FavouriteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val BASEIMAGEURL = "http://image.tmdb.org/t/p/w780"
        private val banner = view.movie_banner
        fun bind(data: MovieEntity, context: Context) {
           Picasso.get().load(BASEIMAGEURL+data.firstBanner).placeholder(R.drawable.placeholder_loading)
                   .error(R.drawable.placeholder_loading)
                   .into(banner)

            banner.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("second_banner", data.secondBanner)
                intent.putExtra("first_banner", data.firstBanner)
                intent.putExtra("movie_title", data.title)
                intent.putExtra("rating", data.voteAverage)
                intent.putExtra("date", data.releaseDate)
                intent.putExtra("language", data.originalLanguage)
                intent.putExtra("Overview", data.overview)
                context.startActivity(intent)
            })

        }
    }

}