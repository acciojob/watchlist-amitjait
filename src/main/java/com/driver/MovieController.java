package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieServiceObj;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){

        movieServiceObj.addMovie(movie);
        return new ResponseEntity("Success", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        movieServiceObj.addDirector(director);
        return new ResponseEntity("Success", HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName){
        boolean check = movieServiceObj.addMovieDirectorPair(movieName, directorName);
        if(!check){
            return new ResponseEntity<>("Please check movie and director name", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name")String movieName){
        try{
            Movie mov = movieServiceObj.getMovieByName(movieName);
            return new ResponseEntity<>(mov, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name")String dirName){
        try{
            Director mov = movieServiceObj.getDirectorByName(dirName);
            return new ResponseEntity<>(mov, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String director){
        try{
            List movies = movieServiceObj.getMoviesByDirectorName(director);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        try {
            List movies = movieServiceObj.findAllMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name){
        try{
            movieServiceObj.deleteDirectorByName(name);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Director not found", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){

        movieServiceObj.deleteAllDirectors();
        return new ResponseEntity("Success", HttpStatus.OK);

    }


}
