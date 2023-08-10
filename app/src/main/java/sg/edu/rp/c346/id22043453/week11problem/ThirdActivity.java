package sg.edu.rp.c346.id22043453.week11problem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    EditText title, genre, year, star;
    Button btnUpdate, btnDelete, btnCancel;
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
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        dbHelper =  new DBHelper(this);

        Intent intent= getIntent();
        movie = (Movie) intent.getSerializableExtra("movie");
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());
        year.setText(String.valueOf(movie.getYear()));
        star.setText(String.valueOf(movie.getStars()));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
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


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie Saving Private Ryan");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteMovie(movie.getID());
                        finish();
                    }
                });

                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want discard the changes");
                myBuilder.setCancelable(false);


                myBuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                });

                myBuilder.setNeutralButton("DO NOT DISCARD", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });


    }

}