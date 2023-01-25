package dev.skan90.dbmovies.service;

import dev.skan90.dbmovies.models.Movie;
import dev.skan90.dbmovies.models.Review;
import dev.skan90.dbmovies.repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    @Mock
    ReviewRepository reviewRepositoryMock;
    @Mock
    MongoTemplate mongoTemplateMock;

    @InjectMocks
    ReviewService reviewService;

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
    void shouldCreateReview() {
        when(reviewRepositoryMock.insert(review))
                .thenReturn(review);
        when(mongoTemplateMock.update(Movie.class))
                .thenCallRealMethod();

        Review reviewCreated = reviewService
                .createReview(review.getBody(), movie.getImdbId());

        verify(reviewRepositoryMock).insert(review);

        assertNotNull(reviewCreated);
        assertEquals(review.getBody(), reviewCreated.getBody());
    }
}