package sg.edu.rp.c346.id22043453.week11problem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        Movie movie = movieList.get(position);

        ImageView imgMovieRating = rowMovie.findViewById(R.id.imageViewRating);
        TextView tvMovieTitle = rowMovie.findViewById(R.id.tvMovieTitle);
        TextView tvMovieYear = rowMovie.findViewById(R.id.tvMovieYear);
        TextView tvMovieGenre = rowMovie.findViewById(R.id.tvMovieGenre);

        imgMovieRating.setImageResource(getRatingImageResource(movie.getStars()));
        tvMovieTitle.setText(movie.getTitle());
        tvMovieYear.setText("Release Year: " + movie.getYear());
        tvMovieGenre.setText("Genre: " + movie.getGenre());


        return rowMovie;


    }

    private int getRatingImageResource(int rating) {
        if (rating == DBHelper.RATING_G) {
            return R.drawable.rating_g;
        } else if (rating == DBHelper.RATING_PG) {
            return R.drawable.rating_pg;
        } else if (rating == DBHelper.RATING_PG13) {
            return R.drawable.rating_pg13;
        } else if (rating == DBHelper.RATING_NC16) {
            return R.drawable.rating_nc16;
        } else if (rating == DBHelper.RATING_M18) {
            return R.drawable.rating_m18;
        } else if (rating == DBHelper.RATING_R21) {
            return R.drawable.rating_r21;
        } else {
            return R.drawable.rating_g; // Replace this with your default rating image
        }
    }

}
