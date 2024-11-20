package com.example.movie4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DataRetriever data;
    private TextView movieName;
    private ImageView imageView;
    private TextView yearCountryGenre;
    private TextView topRating;
    private TextView movieDescription;
    private TextView movieDirector;
    private String yearCountryGenreStr;
    private String topRatingStr;
    private String directorStr;

    private void loadImage(String url) {
        Picasso.get()
                .load(url)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(imageView);
    }

    private void getDataFromApi() {
        Random random = new Random();
        int id = random.nextInt(250) + 1;

        Call<Movie> call = data.apiService.getData(id);
        call.enqueue(new Callback<Movie>() {

            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    loadImage(response.body().getImageurl());
                    yearCountryGenreStr = response.body().getYear() + ", " +
                            response.body().getCountry() + ", " +
                            response.body().getGenre();
                    directorStr = "Режиссёр: " + response.body().getDirector();
                    topRatingStr = " Рейтинг: " + response.body().getRating();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            movieName.setText(response.body().getName());
                            movieDescription.setText(response.body().getDescription());
                            yearCountryGenre.setText(yearCountryGenreStr);
                            movieDirector.setText(directorStr);
                            topRating.setText(topRatingStr);

                        }
                    });
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Response not successful",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                Toast.makeText(
                        MainActivity.this,
                        "An error occurred during networking",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        movieName = findViewById(R.id.movieName);
        movieDescription = findViewById(R.id.movieDescription);
        Button rand_button = findViewById(R.id.rand_button);
        imageView = findViewById(R.id.imageView);
        topRating = findViewById(R.id.topRating);
        yearCountryGenre = findViewById(R.id.yearCountryGenre);
        movieDirector = findViewById(R.id.movieDirector);
        data = new DataRetriever();



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        rand_button.setOnClickListener(v -> getDataFromApi());
    }
}


