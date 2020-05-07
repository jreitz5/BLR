package commands;

import commands.GetLandlords;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    private String url = "jdbc:sqlite:data/blr_testing.sqlite3";

    @Test
    public void testPullLandlords() {
        GetLandlords test = new GetLandlords(url);

        List<String> landlords = test.getNames();

        assertTrue(landlords.contains("Lance Bay"));
        assertTrue(landlords.contains("Edward Kazarian"));
        assertTrue(landlords.contains("Walter Bronhard"));
        assertTrue(landlords.contains("Steve Hardcastle"));
        assertTrue(landlords.contains("Tracy Keller"));
        assertTrue(landlords.contains("Michael Raynus"));
        assertTrue(landlords.contains("Bethany Hughes"));
    }

    @Test
    public void testSumbitReview() {
        SubmitReview test = new SubmitReview(url);

        test.addReviewToDB(99999, 100, null, 4,
                "Great landlord!", 0, "May 4th, 1999", 0);

    }

    @Test
    public void testGetReviews() {
        GetReviews test = new GetReviews(url);

        List<List<String>> res = test.getReviewsAsList();

        for (int i = 0; i < res.size(); i++) {
            List<String> review = res.get(i);
            assertNotNull(review.get(0));
            assertNotNull(review.get(4));
            assertNotNull(review.get(5));
        }
    }

    @Test
    public void testRegisterUser() {
        RegisterUser test = new RegisterUser(url);
        test.register("User", "Test", "test@test.com", 0, "","");
    }

    @Test
    public void testApproveReview() {
        ApproveReview test = new ApproveReview(url);
        test.approve(100009);
    }
}
