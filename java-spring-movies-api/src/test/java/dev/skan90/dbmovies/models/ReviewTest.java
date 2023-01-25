package dev.skan90.dbmovies.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ReviewTest {

    Review review;

    @Test
    void testing() {
        String body = "Body";
        review = new Review(body);
        String bodyGotFromReviewOverflow = review.getBody();

        assertNotNull(bodyGotFromReviewOverflow);
        assertEquals(body, bodyGotFromReviewOverflow);
    }

}