package dev.skan90.dbmovies.controllers;

import dev.skan90.dbmovies.models.Movie;
import dev.skan90.dbmovies.models.Review;
import dev.skan90.dbmovies.service.ReviewService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class ReviewControllerTest {

    @Mock
    ReviewService reviewServiceMock;

    @InjectMocks
    ReviewController reviewController;

    Review review;
    Movie movie;

    @BeforeEach
    void setUp() {
        review = new Review("New review made");
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        movie = new Movie();
        movie.setId(new ObjectId());
        movie.setImdbId("tt3915174");
        movie.setTitle("Puss in Boots: The Last Wish");
        movie.setReleaseDate("2022-12-21");
        movie.setTrailerLink("https://www.youtube.com/watch?v=tHb7WlgyaUc");
        movie.setGenres(List.of("Animation", "Action", "Adventure", "Comedy", "Family"));
        movie.setPoster("https://image.tmdb.org/t/p/w500/1NqwE6LP9IEdOZ57NCT51ftHtWT.jpg");
        movie.setReviewIds(reviewList);
    }

    @Test
    void shouldCreateANewReviewInMovieReviewListAndReturnCreatedStatus() {

        when(reviewServiceMock.createReview(review.toString(), movie.getImdbId()))
                .thenReturn(review);

//        reviewController.createReview();
    }
}