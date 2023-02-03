package dev.skan90.dbmovies.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.skan90.dbmovies.models.Review;
import dev.skan90.dbmovies.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class ReviewControllerMockMvcUnitTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    public void createReview_ShouldReturnCreatedStatus() throws Exception {
        // Given
        Map<String, String> payload = new HashMap<>();
        payload.put("reviewBody", "This is a great movie");
        payload.put("imdbId", "tt0111161");

        Review review = new Review("This is a great movie");

        when(reviewService.createReview("This is a great movie", "tt0111161"))
                .thenReturn(review);

        // When & Then
        mockMvc.perform(post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.body", is("This is a great movie")));
    }
}

