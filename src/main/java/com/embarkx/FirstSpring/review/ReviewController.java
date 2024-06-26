package com.embarkx.FirstSpring.review;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review){
       boolean isReviewSaved= reviewService.addReview(companyId,review);
       if (isReviewSaved)
        return new ResponseEntity<>("review added successfully",HttpStatus.OK);
       return new ResponseEntity<>("review not saved",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewsId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,@PathVariable Long reviewsId){
        return new ResponseEntity<>(reviewService.getReview(companyId,reviewsId),HttpStatus.OK);

    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated= reviewService.updateReview(companyId,reviewId,review);
        if (isReviewUpdated){
            return new ResponseEntity<>("Review updarted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("not updated",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean isReviewDeleted=reviewService.deleteReview(companyId,reviewId);
        if (isReviewDeleted){
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
    }
}
