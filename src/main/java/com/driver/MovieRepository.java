package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MovieRepository {

    HashMap<String, Movie> movies = new HashMap<>();
    HashMap<String, Director> directors = new HashMap<>();
    HashMap<String, List<Movie>> movDir = new HashMap<>();

    public void addMovie(Movie movie){
        movies.put(movie.getName(), movie);
    }

    public void addDirector(Director director){
        directors.put(director.getName(), director);
    }
    public boolean addMovieDirectorPair(String movieName, String directorName){
        boolean mov = false;

        for(Movie movie : movies.values()){
            if(movie.getName().equals(movieName)){
                mov = true;
            }
        }

        boolean dir = false;

        for(Director director : directors.values()){
            if(director.getName().equals(directorName)){
                dir = true;
            }
        }

        if(!mov || !dir){
            return false;
        }

        if(movDir.containsKey(directorName)){
            movDir.get(directorName).add(movies.get(movieName));
        }else{
            movDir.put(directorName, new ArrayList<>());
            movDir.get(directorName).add(movies.get(movieName));
        }

        return true;
    }

    public Movie getMovieByName(String movieName){

        if(movies.containsKey(movieName)){
            return movies.get(movieName);
        }

        return null;
    }

    public Director getDirectorByName(String dirName){

        if(directors.containsKey(dirName)){
            return directors.get(dirName);
        }

        return null;
    }

    public List<Movie> getMoviesByDirectorName(String dirName){
        if(movDir.containsKey(dirName)){
            return movDir.get(dirName);
        }

        return new ArrayList<>();
    }

    public List<Movie> findAllMovies(){
        List<Movie> movieList = new ArrayList<>();

        for(Movie mov : movies.values()){
            movieList.add(mov);
        }

        return movieList;
    }

    public void deleteDirectorByName(String director){
        List<Movie> mov;
        if(movDir.containsKey(director)){
            mov = movDir.get(director);
        }else{
            mov = new ArrayList<>();
        }

        for(int i=0; i<mov.size(); i++){
            if(movies.containsKey(mov.get(i).getName())){
                movies.remove(mov.get(i).getName());
            }
        }

        if(directors.containsKey(director)){
            directors.remove(director);
        }

        if(movDir.containsKey(director)){
            movDir.remove(director);
        }
    }
    public void deleteAllDirectors(){
        List<String> allDir = new ArrayList<>();

        for(String dir : directors.keySet()){
            allDir.add(dir);
        }

        for(int i=0; i<allDir.size(); i++){
            deleteDirectorByName(allDir.get(i));
        }
    }
}
