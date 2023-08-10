package sg.edu.rp.c346.id22043453.week11problem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "movies.db";
    private static final String TABLE_MOVIE = "movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";
    public static final int RATING_G = 1;
    public static final int RATING_PG = 2;
    public static final int RATING_PG13 = 3;
    public static final int RATING_NC16 = 4;
    public static final int RATING_M18 = 5;
    public static final int RATING_R21 = 6;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_YEAR + " INTEGER," // Add space before INTEGER
                + COLUMN_STARS + " INTEGER)"; // Add space before INTEGER

        db.execSQL(createTableSql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        // Create table(s) again
        onCreate(db);

    }

    public void insertMovie(String title, String genre, int year, int stars) {

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_MOVIE, null, values);
        // Close the database connection
        db.close();
    }


    public ArrayList<Movie> getMovie() {
        ArrayList<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                //int stars = cursor.getInt(cursor.getColumnIndex(COLUMN_STARS));
                Movie movie = new Movie(id, title, genre, year, stars);
                movieList.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movieList;
    }

    public ArrayList<Movie> getPG13Movies(int rating) {
        ArrayList<Movie> moviesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_STARS};
        String selection = COLUMN_STARS + " = ?";
        String[] selectionArgs = {String.valueOf(rating)};
        Cursor cursor = db.query(TABLE_MOVIE, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String genre = cursor.getString(cursor.getColumnIndex(COLUMN_GENRE));
                int year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR));
                int movieRating = cursor.getInt(cursor.getColumnIndex(COLUMN_STARS));

                // Create a new Movies object and add it to the list
                Movie movie = new Movie(id, title, genre, year, movieRating);
                moviesList.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return moviesList;
    }

    public ArrayList<Integer> getSpinnerYears() {
        ArrayList<Integer> yearsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_YEAR};
        Cursor cursor = db.query(true, TABLE_MOVIE, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int year = cursor.getInt(0);
                yearsList.add(year);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return yearsList;
    }

    public ArrayList<Movie> getMovieYear(int year) {
        ArrayList<Movie> songList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_STARS};
        String selection = COLUMN_YEAR + " = ?";
        String[] selectionArgs = {String.valueOf(year)};
        Cursor cursor = db.query(TABLE_MOVIE, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                //int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Movie movie = new Movie(id, title, genre, year, stars);
                songList.add(movie);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return songList;

    }

    public void updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_YEAR, movie.getYear());
        values.put(COLUMN_STARS, movie.getStars());
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(movie.getID())};
        db.update(TABLE_MOVIE, values, selection, selectionArgs);
        db.close();

    }

    public void deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_ID + " =?";
        String[] selectionArgs = {String.valueOf(id)};
        db.delete(TABLE_MOVIE, selection, selectionArgs);
        db.close();

    }



}
