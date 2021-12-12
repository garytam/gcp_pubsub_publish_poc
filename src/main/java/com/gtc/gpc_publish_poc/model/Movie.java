package com.gtc.gpc_publish_poc.model;

import java.util.Objects;

public class Movie implements Comparable<Movie>, Cloneable {

    private String name;
    private int releaseYear;
    private String director;
    private double boxofficeGross;

    public Movie(String name, int releaseYear, String director, double boxofficeGross) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.director = director;
        this.boxofficeGross = boxofficeGross;
    }

    public int compareTo(Movie e){ //implementing abstract method.
        return (name + releaseYear).compareTo(e.name + e.releaseYear);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", director='" + director + '\'' +
                ", boxofficeGross=" + boxofficeGross +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return releaseYear == movie.releaseYear && Double.compare(movie.boxofficeGross, boxofficeGross) == 0 && Objects.equals(name, movie.name) && Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseYear, director, boxofficeGross);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getBoxofficeGross() {
        return boxofficeGross;
    }

    public void setBoxofficeGross(double boxofficeGross) {
        this.boxofficeGross = boxofficeGross;
    }
}

