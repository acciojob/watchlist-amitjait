package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepositoryObj;

    public void addMovie(Movie movie){
        movieRepositoryObj.addMovie(movie);
    }

    public void addDirector(Director director){
        movieRepositoryObj.addDirector(director);
    }
    public boolean addMovieDirectorPair(String movieName, String directorName){
        return movieRepositoryObj.addMovieDirectorPair(movieName, directorName);
    }

    public Movie getMovieByName(String movieName){
        return movieRepositoryObj.getMovieByName(movieName);
    }

    public Director getDirectorByName(String dirName){
        return movieRepositoryObj.getDirectorByName(dirName);
    }

    public List<Movie> getMoviesByDirectorName(String dirName){
        return movieRepositoryObj.getMoviesByDirectorName(dirName);
    }

    public List<Movie> findAllMovies(){
        return movieRepositoryObj.findAllMovies();
    }

    public void deleteDirectorByName(String director){
        movieRepositoryObj.deleteDirectorByName(director);
    }

    public void deleteAllDirectors(){
        movieRepositoryObj.deleteAllDirectors();
    }
}
