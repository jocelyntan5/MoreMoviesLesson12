package sg.edu.rp.c346.id22043453.week11problem;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String title;
    private String genre;
    private int year;
    private int stars;

    public Movie(int id, String title, String genre, int year, int stars) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.stars = stars;
    }





    @NonNull
    @Override
    public String toString() {
        return id + "\n" + title + "\n" + genre + "\n" + year + "\n" + stars;
    }

    public int getID() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public String getGenre() {

        return genre;
    }

    public int getYear() {

        return year;
    }

    public int getStars() {

        return stars;
    }
    public void setTitle(String title) {

        this.title = title;
    }

    public void setGenre(String genre) {

        this.genre = genre;
    }

    public void setYear(int year) {

        this.year = year;
    }

    public void setStar(int stars) {

        this.stars = stars;
    }


}
