package sg.edu.rp.c346.id22043453.week11problem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    Spinner spinner;
    Button btnPG13;
    ArrayAdapter<Movie> adapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        dbHelper = new DBHelper(this);
        lv = findViewById(R.id.lv);
        spinner = findViewById(R.id.spinner);
        btnPG13 = findViewById(R.id.buttonPG13);

        ArrayList<Movie> songList = dbHelper.getMovie();
        adapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie clickMovie = (Movie) parent.getItemAtPosition(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("movie", clickMovie);
                startActivity(intent);
            }
        });

        btnPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Movie> pg13MoviesList = dbHelper.getPG13Movies(DBHelper.RATING_PG13);
                adapter.clear();
                adapter.addAll(pg13MoviesList);
                adapter.notifyDataSetChanged();
            }
        });

        ArrayList<Integer> spinneryears = dbHelper.getSpinnerYears();
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinneryears);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int mainyear = (int) parent.getItemAtPosition(position);
                ArrayList<Movie> list = dbHelper.getMovieYear(mainyear);
                adapter.clear();
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void onResume() {
        super.onResume();
        ArrayList<Movie> movieList = dbHelper.getMovie();
        adapter.clear();
        adapter.addAll(movieList);
        adapter.notifyDataSetChanged();
    }


}