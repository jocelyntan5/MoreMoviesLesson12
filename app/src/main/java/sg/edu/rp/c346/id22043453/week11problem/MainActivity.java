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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etMovieTitle;
    EditText etGenre;
    EditText etYear;
    Button btnInsert;
    Button btnShowList;
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


        ArrayList<Movie> sList = new ArrayList<>();
        sList.add(m1);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create the DBHelper object, passing in the
                // activity's Context


                // Insert a task
                String title = etMovieTitle.getText().toString().trim();
                String genre = etGenre.getText().toString().trim();
                String yearToString = etYear.getText().toString().trim();

                int year = Integer.parseInt(yearToString);
                //int stars = getStarsSelected();

                String selectRatingToString = spinnerRating.getSelectedItem().toString();
                int selectRatingToInt = Integer.parseInt(selectRatingToString.split(" ")[0]);

                DBHelp.insertMovie(title, genre, year, selectRatingToInt);


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


        ArrayList<Integer> spinneryears = DBHelp.getSpinnerYears();
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinneryears);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRating.setAdapter(spinnerAdapter);

        spinnerRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int mainyear = (int) parent.getItemAtPosition(position);
                ArrayList<Movie> list = DBHelp.getMovieYear(mainyear);
                adapter.clear();
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    /*
    private int getStarsSelected() {

        int starsId = rgStars.getCheckedRadioButtonId();

        if (starsId == R.id.radioButton1) {
            return 1;
        }
        else if (starsId == R.id.radioButton2) {
            return 2;
        }
        else if (starsId == R.id.radioButton3) {
            return 3;
        }
        else if (starsId == R.id.radioButton4) {
            return 4;
        }
        else if (starsId == R.id.radioButton5) {
            return 5;
        }

        return 0;

    }
    */
}