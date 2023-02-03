package dev.skan90.dbmovies.controllers;

import dev.skan90.dbmovies.models.Movie;
import dev.skan90.dbmovies.service.MovieService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {
    @Mock
    MovieService movieServiceMock;

    @InjectMocks
    MovieController movieController;
    public String str = "xyz";

    Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(new ObjectId());
        movie.setImdbId("tt3915174");
        movie.setTitle("Puss in Boots: The Last Wish");
    }

    @Test
    void shouldGetListOfAllMovies() {
        when(movieServiceMock.findAllMovies()).thenReturn(List.of(movie));

        ResponseEntity<List<Movie>> allMoviesGotFromDb = movieController.getMovies();

        assertNotNull(allMoviesGotFromDb);
        assertNotNull(allMoviesGotFromDb.getBody());
        assertEquals(HttpStatus.OK, allMoviesGotFromDb.getStatusCode());
        assertEquals(movie.getId(), allMoviesGotFromDb.getBody().get(0).getId());
        assertEquals(movie.getImdbId(), allMoviesGotFromDb.getBody().get(0).getImdbId());
        assertEquals(movie.getTitle(), allMoviesGotFromDb.getBody().get(0).getTitle());
    }

    @Test
    void shouldGetSingleMovieFromImdbId() {
        when(movieServiceMock.singleMovie(movie.getImdbId())).thenReturn(Optional.of(movie));

        ResponseEntity<Optional<Movie>> singleMovieFound = movieController.getSingleMovie(movie.getImdbId());

        assertNotNull(singleMovieFound);
        assertNotNull(singleMovieFound.getBody());
        assertEquals(HttpStatus.OK, singleMovieFound.getStatusCode());
        assertEquals(movie.getId(), singleMovieFound.getBody().get().getId());
        assertEquals(movie.getImdbId(), singleMovieFound.getBody().get().getImdbId());
        assertEquals(movie.getTitle(), singleMovieFound.getBody().get().getTitle());
    }

    @Test
    void shouldReturnNotFoundForEmptyList() {
        when(movieServiceMock.findAllMovies()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Movie>> allMoviesGotFromDb = movieController.getMovies();

        assertNotNull(allMoviesGotFromDb);
        assertEquals(HttpStatus.NOT_FOUND, allMoviesGotFromDb.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundForEmptyOptional() {
        when(movieServiceMock.singleMovie(str)).thenReturn(Optional.empty());

        ResponseEntity<Optional<Movie>> singleMovieFound = movieController.getSingleMovie(str);

        assertNotNull(singleMovieFound);
        assertEquals(HttpStatus.NOT_FOUND, singleMovieFound.getStatusCode());
    }

    @Test
    void shouldReturnInternalServerError() {
        when(movieServiceMock.findAllMovies()).thenReturn(null);

        ResponseEntity<List<Movie>> allMoviesGotFromDb = movieController.getMovies();

        assertNotNull(allMoviesGotFromDb);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, allMoviesGotFromDb.getStatusCode());
    }

}