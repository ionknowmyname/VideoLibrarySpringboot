package com.testingjava8.videolibrary;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/movies")
@AllArgsConstructor
public class MovieController {

//   @Autowired
    private final MovieService movieService;



    @GetMapping(path = "/all")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping(path = "id/{movieId}")
    public Optional<Movie> getMovieById(@PathVariable("movieId") String movieId){ // @PathVariable("movieId") String movieId

        // Optional<Movie> = MovieRepository.
        return movieService.getMovieById(movieId);
    }

    @GetMapping(path = "title/{movieTitle}")
    public Optional<Movie> getMovieByTitle(@PathVariable("movieTitle") String movieTitle){
        return movieService.getMovieByTitle(movieTitle);
    }

    @GetMapping(path = "type/{movieType}")
    public List<Movie> getMovieByType(@PathVariable("movieType") String movieType){
        return movieService.getMovieByType(movieType);
    }

    @PostMapping(path = "/add")
    public String addNewMovie(@RequestBody Movie movie){
        return movieService.addNewMovie(movie);
    }

    @DeleteMapping(path ="delete/{movieId}")
    public String deleteMovie(@PathVariable("movieId" ) String movieId){
        return movieService.deleteMovie(movieId);
    }

    @PutMapping (path = "update/{movieId}")
    public String updateMovie(
            @PathVariable("movieId") String movieId,
            @RequestParam(required = false) String movieTitle,
            @RequestParam(required = false) String movieType,
            @RequestParam(required = false) String movieGenre,
            @RequestParam(required = false) LocalDate movieReleaseYear){
        return movieService.updateMovie(movieId, movieTitle, movieType, movieGenre, movieReleaseYear);
    }


}
