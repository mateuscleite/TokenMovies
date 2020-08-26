package mateuscleite.github.tokenmovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mateuscleite.github.tokenmovies.R;
import mateuscleite.github.tokenmovies.databinding.MainActivityBinding;
import mateuscleite.github.tokenmovies.models.Movie;
import mateuscleite.github.tokenmovies.network.MovieClient;
import mateuscleite.github.tokenmovies.viewmodels.MainActivityViewModel;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private ArrayList<MainActivityViewModel> movies = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private static MovieListAdapter instance;

    public static MovieListAdapter getInstance(Context context, ArrayList<MainActivityViewModel> movies){
        if(instance == null){
            instance = new MovieListAdapter(context, movies);
        }
        return instance;
    }

    public MovieListAdapter(Context context, ArrayList<MainActivityViewModel> movies){
        this.context = context;
        this.movies = movies;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private MainActivityBinding mainActivityBinding;

        public ViewHolder(@NonNull MainActivityBinding mainActivityBinding){
            super(mainActivityBinding.getRoot());
            this.mainActivityBinding = mainActivityBinding;
        }

        public void bind(MainActivityViewModel mainActivityViewModel){
            this.mainActivityBinding.setMoviesmodel(mainActivityViewModel);
            mainActivityBinding.executePendingBindings();
        }

        public MainActivityBinding getMainActivityBinding(){
            return mainActivityBinding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        MainActivityBinding mainActivityBinding = DataBindingUtil.inflate(layoutInflater, R.layout.movie_item, parent, false);
        return new ViewHolder(mainActivityBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainActivityViewModel mainActivityViewModel = movies.get(position);
        holder.bind(mainActivityViewModel);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
