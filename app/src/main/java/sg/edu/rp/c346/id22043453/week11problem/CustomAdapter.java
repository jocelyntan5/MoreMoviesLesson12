package sg.edu.rp.c346.id22043453.week11problem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {

        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowMovie = convertView;

        if (rowMovie == null) {
            LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowMovie = inflater.inflate(R.layout.row, parent, false);
        }


        TextView textViewSongTitle = rowMovie.findViewById(R.id.tvSongTitle);
        TextView textViewSongYear = rowMovie.findViewById(R.id.tvSongYear);
        TextView textViewSongRating = rowMovie.findViewById(R.id.tvSongRating);
        TextView textViewSongGenre = rowMovie.findViewById(R.id.tvSongGenre);

        Movie currentSong = movieList.get(position);

        String[] songDetails = new String[] {
                "Title: " + currentSong.getTitle(),
                "Genre: " + currentSong.getGenre(),
                "Year Released: " + currentSong.getYear(),
                "Stars: " + getMovieStarRating(currentSong.getStars())
        };


        textViewSongTitle.setText(currentSong.getTitle());
        textViewSongYear.setText(String.valueOf(currentSong.getYear()));
        textViewSongRating.setText(getMovieStarRating(currentSong.getStars()));
        textViewSongGenre.setText(currentSong.getGenre());

        return rowMovie;

    }

    private String getMovieStarRating (int songStars) {
        switch (songStars) {
            case 1:
                return "⭐";
            case 2:
                return "⭐⭐";
            case 3:
                return "⭐⭐⭐";
            case 4:
                return "⭐⭐⭐⭐";
            case 5:
                return "⭐⭐⭐⭐⭐";
            default:
                return "Nothing";
        }
    }


}
