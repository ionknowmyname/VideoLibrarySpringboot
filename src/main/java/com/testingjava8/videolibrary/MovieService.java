package com.testingjava8.videolibrary;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public String addNewMovie(Movie movie) {
        Optional<Movie> movieOptional = movieRepository.findMovieByTitle(movie.getTitle());

        if (movieOptional.isPresent()) {
            throw new IllegalStateException("Movie title already exists");
        }

        movieRepository.save(movie);

        return "New movie with title '" + movie.getTitle() + "' added";
    }

    public Optional<Movie> getMovieById(String movieId) {
        Optional<Movie> movieOptional = movieRepository.findMovieById(movieId); // movie.getId()

        if (movieOptional.isPresent()) {
            // return Optional.of(movieOptional.get());
            return movieOptional;
        }
        throw new IllegalStateException("Movie with ID does not exist");
    }

    public Optional<Movie> getMovieByTitle(String movieTitle) {
        Optional<Movie> movieOptional = movieRepository.findMovieByTitle(movieTitle);

        if (movieOptional.isPresent()) {
            // return Optional.of(movieOptional.get());
            return movieOptional;
        }
        throw new IllegalStateException("Movie with ID does not exist");
    }

    public List<Movie> getMovieByType(String movieType) {
        List<Movie> movieList = movieRepository.findMovieByType(movieType);
        // .orElseThrow(() -> new IllegalStateException("movie with type " + movieType + " does not exist"));

        if (movieList != null && !movieList.isEmpty()) {
            // List<Movie> movies = new ArrayList<>();
            // movieOptional.ifPresent(movies::add);
            return movieList;

            //return List<Movie> movieOptional;
        }
        throw new IllegalStateException("Movie with ID does not exist");
    }


    public String deleteMovie(String movieId) {

        boolean exists = movieRepository.existsById(movieId);

        if (!exists){
            throw new IllegalStateException("Movie with id " + movieId + " does not exist");
        }

        movieRepository.deleteById(movieId);

        return "Movie with ID '" + movieId + "' deleted successfully";

        // try delete by title
    }

    public String updateMovie(String movieId,
                              String movieTitle,
                              String movieType,
                              String movieGenre,
                              LocalDate movieReleaseYear) {
        Movie movie = movieRepository.findMovieById(movieId)
                .orElseThrow(() -> new IllegalStateException("movie with id " + movieId + " does not exist"));

        if (movieTitle != null && movieTitle.length() > 0 && !Objects.equals(movie.getTitle(), movieTitle)){
            // movie.getTitle() = new title, movieTitle = current title

            movie.setTitle(movieTitle);
            return "Movie title with ID '" + movieId + "' has been successfully updated";
        }

        if (movieType != null && movieType.length() > 0 && !Objects.equals(movie.getType(), movieType)){
            movie.setType(movieType);
            return "Movie type with ID '" + movieId + "' has been successfully updated";
        }

        if (movieGenre != null && movieGenre.length() > 0 && !Objects.equals(movie.getGenre(), movieGenre)){
            movie.setGenre(movieGenre);
            return "Movie Genre with ID '" + movieId + "' has been successfully updated";
        }

        if (movieReleaseYear != null && !Objects.equals(movie.getReleaseYear(), movieReleaseYear)){
            movie.setReleaseYear(movieReleaseYear);
            return "Movie release year with ID '" + movieId + "' has been successfully updated";
        }

        return "Movie with ID '" + movieId + "' has remained unchanged";
    }

    // also find by type, genre & releaseYear
}
