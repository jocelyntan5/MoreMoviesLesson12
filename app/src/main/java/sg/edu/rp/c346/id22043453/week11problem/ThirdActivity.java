package sg.edu.rp.c346.id22043453.week11problem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    EditText title, genre, year, star;
    Button updbtn, dtlbtn;
    Movie movie;
    DBHelper dbHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        title = findViewById(R.id.editTextTitle);
        genre = findViewById(R.id.editTextGenre);
        year = findViewById(R.id.editTextYear);
        star = findViewById(R.id.editTextStars);
        updbtn = findViewById(R.id.buttonUpdate);
        dtlbtn = findViewById(R.id.buttonDelete);
        dbHelper =  new DBHelper(this);

        Intent intent= getIntent();
        movie = (Movie) intent.getSerializableExtra("song");
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());
        year.setText(String.valueOf(movie.getYear()));
        star.setText(String.valueOf(movie.getStars()));
        updbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title1 =  title.getText().toString().trim();
                String singer1 =  genre.getText().toString().trim();
                int year1 =  Integer.parseInt(year.getText().toString().trim());
                int star1 =  Integer.parseInt(star.getText().toString().trim());
                movie.setTitle(title1);
                movie.setGenre(singer1);
                movie.setYear(year1);
                movie.setStar(star1);
                dbHelper.updateMovie(movie);
            }
        });
        dtlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteMovie(movie.getID());
                finish();
            }
        });
    }

}