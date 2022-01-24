package com.testingjava8.videolibrary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<Movie> findMovieByTitle(String movieTitle);

    // @Override
    Optional<Movie> findMovieById(String movieId);

    List<Movie> findMovieByType(String movieType); // try list instead of optional
}
