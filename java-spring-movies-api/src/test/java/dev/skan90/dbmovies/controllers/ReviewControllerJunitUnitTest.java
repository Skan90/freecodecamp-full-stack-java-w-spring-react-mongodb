package dev.skan90.dbmovies.controllers;

import dev.skan90.dbmovies.models.Review;
import dev.skan90.dbmovies.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerJunitUnitTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @Test
    public void createReview_ShouldReturnCreatedStatus() {
        // Given
        Map<String, String> payload = new HashMap<>();
        payload.put("reviewBody", "This is a great movie");
        payload.put("imdbId", "tt0111161");

        Review review = new Review("This is a great movie");

        when(reviewService.createReview("This is a great movie", "tt0111161"))
                .thenReturn(review);

        // When
        ResponseEntity<Review> response = reviewController.createReview(payload);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getBody()).isEqualTo("This is a great movie");
    }
}
