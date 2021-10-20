package com.mbytessolution.trialxcinema.view.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.mbytessolution.trialxcinema.Adapters.FavouriteMoviesAdapter
import com.mbytessolution.trialxcinema.R
import com.mbytessolution.trialxcinema.viewmodel.FavouriteMoviesViewModel
import kotlinx.android.synthetic.main.fragment_favourite.*


class FavouriteFragment : Fragment() {

    lateinit var favouriteMoviesAdapter: FavouriteMoviesAdapter;
    lateinit var favouriteMoviesViewModel: FavouriteMoviesViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favMovieRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2);
            favouriteMoviesAdapter = FavouriteMoviesAdapter(context)
            adapter = favouriteMoviesAdapter;

        }

        favouriteMoviesViewModel = ViewModelProvider(this).get(FavouriteMoviesViewModel::class.java)
        favouriteMoviesViewModel.getAllUserObservers().observe(viewLifecycleOwner, Observer {

                favouriteMoviesAdapter.setListData(ArrayList(it))
                favouriteMoviesAdapter.notifyDataSetChanged()
                if (progress_bar1.visibility==View.VISIBLE) {
                    progress_bar1.visibility=View.GONE
                }

        })



    }

}