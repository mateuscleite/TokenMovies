package mateuscleite.github.tokenmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mateuscleite.github.tokenmovies.MovieDetailsActivity;
import mateuscleite.github.tokenmovies.R;
import mateuscleite.github.tokenmovies.databinding.MainActivityBinding;
import mateuscleite.github.tokenmovies.viewmodels.MainActivityViewModel;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private ArrayList<MainActivityViewModel> movies;
    private Context context;
    private LayoutInflater layoutInflater;

    public MovieListAdapter(Context context, ArrayList<MainActivityViewModel> movies) {
        this.context = context;
        this.movies = movies;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MainActivityBinding mainActivityBinding;

        public ViewHolder(@NonNull MainActivityBinding mainActivityBinding) {
            super(mainActivityBinding.getRoot());
            this.mainActivityBinding = mainActivityBinding;
        }

        public void bind(MainActivityViewModel mainActivityViewModel) {
            this.mainActivityBinding.setMoviesmodel(mainActivityViewModel);
            mainActivityBinding.executePendingBindings();
        }

        public MainActivityBinding getMainActivityBinding() {
            return mainActivityBinding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        MainActivityBinding mainActivityBinding = DataBindingUtil.inflate(layoutInflater, R.layout.movie_item, parent, false);
        return new ViewHolder(mainActivityBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainActivityViewModel mainActivityViewModel = movies.get(position);
        holder.bind(mainActivityViewModel);
        holder.mainActivityBinding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("intentMovieId", movies.get(position).movieModel.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
