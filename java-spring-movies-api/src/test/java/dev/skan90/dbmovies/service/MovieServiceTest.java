package dev.skan90.dbmovies.service;

import dev.skan90.dbmovies.models.Movie;
import dev.skan90.dbmovies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    MovieRepository movieRepositoryMock;

    @InjectMocks
    MovieService movieService;

    Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(new ObjectId());
        movie.setImdbId("tt3915174");
        movie.setTitle("Puss in Boots: The Last Wish");
        movie.setReleaseDate("2022-12-21");
        movie.setTrailerLink("https://www.youtube.com/watch?v=tHb7WlgyaUc");
        movie.setGenres(List.of("Animation", "Action", "Adventure", "Comedy", "Family"));
        movie.setPoster("https://image.tmdb.org/t/p/w500/1NqwE6LP9IEdOZ57NCT51ftHtWT.jpg");
    }

    @Test
    void findAllMovies() {
        List<Movie> listOfMovies = List.of(movie);
        when(movieRepositoryMock.findAll()).thenReturn(listOfMovies);

        List<Movie> moviesFound = movieService.findAllMovies();

        assertNotNull(moviesFound);
        assertEquals(listOfMovies, moviesFound);
    }

    @Test
    void singleMovie() {
        when(movieRepositoryMock.findMovieByImdbId(movie.getImdbId()))
                .thenReturn(Optional.of(movie));

        Optional<Movie> movieFound = movieService
                .singleMovie(movie.getImdbId());

        assertNotNull(movieFound);
        assertEquals(Optional.of(movie), movieFound);
        assertEquals(movie.getImdbId(), movieFound.get().getImdbId());
        assertEquals(movie.getId(), movieFound.get().getId());
    }
}