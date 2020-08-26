package mateuscleite.github.tokenmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mateuscleite.github.tokenmovies.adapters.MovieListAdapter;
import mateuscleite.github.tokenmovies.models.Movie;
import mateuscleite.github.tokenmovies.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recViewMoviesList;
    private MainActivityViewModel mainActivityViewModel;
    private MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recViewMoviesList = findViewById(R.id.recViewMoviesList);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getMutableLiveData().observe(this, new Observer<ArrayList<MainActivityViewModel>>() {
            @Override
            public void onChanged(ArrayList<MainActivityViewModel> mainActivityViewModels) {
                adapter = MovieListAdapter.getInstance( MainActivity.this, mainActivityViewModels);
                recViewMoviesList.setAdapter(adapter);
                recViewMoviesList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });

        TextView developerLink = findViewById(R.id.developedBy);
        developerLink.setMovementMethod(LinkMovementMethod.getInstance());
    }



}