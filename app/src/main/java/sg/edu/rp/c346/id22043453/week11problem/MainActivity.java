package sg.edu.rp.c346.id22043453.week11problem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etMovieTitle, etGenre, etYear;
    Button btnInsert, btnShowList;
    Spinner spinnerRating;
    ListView lvSong;
    ArrayAdapter<Movie> adapter;
    DBHelper DBHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etMovieTitle = findViewById(R.id.editTextMovieTitle);
        etGenre = findViewById(R.id.editTextGenre); // Corrected variable name
        etYear = findViewById(R.id.editTextYear);
        spinnerRating = findViewById(R.id.spinnerRating);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShowList = findViewById(R.id.buttonShowList);
        lvSong = findViewById(R.id.listViewMovie);
        DBHelp = new DBHelper(this);


        Movie m1 = new Movie(1, "Hello", "Treasure", 2022, 5);


        ArrayList<Movie> mList = new ArrayList<>();
        mList.add(m1);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.movie_ratings));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRating.setAdapter(spinnerAdapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Insert a task
                String title = etMovieTitle.getText().toString().trim();
                String genre = etGenre.getText().toString().trim();
                String yearToString = etYear.getText().toString().trim();

                int year = Integer.parseInt(yearToString);

                String selectRatingToString = spinnerRating.getSelectedItem().toString();
                int selectRatingToInt = getSelectRatingValue(selectRatingToString);

                DBHelp.insertMovie(title, genre, year, selectRatingToInt);

                Toast.makeText(MainActivity.this, "Movie inserted", Toast.LENGTH_SHORT).show();
                clearAllFields();

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    private int getSelectRatingValue(String rating) {
        switch (rating) {
            case "G":
                return 1;
            case "PG":
                return 2;
            case "PG13":
                return 3;
            case "NC16":
                return 4;
            case "M18":
                return 5;
            case "R21":
                return 6;
            default:
                return 0;
        }
    }

    private void clearAllFields() {
        etMovieTitle.setText("");
        etGenre.setText("");
        etYear.setText("");
    }

}