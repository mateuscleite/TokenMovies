package mateuscleite.github.tokenmovies;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mateuscleite.github.tokenmovies.adapters.MovieListAdapter;
import mateuscleite.github.tokenmovies.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    //UI related attributes
    private ProgressBar progressBar;
    private RecyclerView recViewMoviesList;
    private MainActivityViewModel mainActivityViewModel;
    private MovieListAdapter adapter;

    //Data sent to an activity
    public static String intentMovieId = "intentMovieId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize UI elements
        recViewMoviesList = findViewById(R.id.recViewMoviesList);
        progressBar = findViewById(R.id.progressBar);
        hideProgressBar();

        //Initialize the ViewModel
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        //Observes the data coming from the API and renders it on the screen
        mainActivityViewModel.getMutableLiveData().observe(this, new Observer<ArrayList<MainActivityViewModel>>() {

            @Override
            public void onChanged(ArrayList<MainActivityViewModel> mainActivityViewModels) {
                adapter = new MovieListAdapter( MainActivity.this, mainActivityViewModels);
                recViewMoviesList.setAdapter(adapter);
                recViewMoviesList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });

        //Observes if data is being requested to determine if the progress bar should be visible
        mainActivityViewModel.progressBar.observe(this, new Observer<Boolean>(){
            @Override
            public void onChanged(Boolean makingRequest) {
                if(makingRequest) showProgressBar();
                else hideProgressBar();
            }
        });

        //Footer has a link that opens the browser
        TextView developerLink = findViewById(R.id.developedBy);
        developerLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    public String getIntentMovieId() {
        return intentMovieId;
    }

}