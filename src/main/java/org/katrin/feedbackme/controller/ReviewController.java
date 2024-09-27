package org.katrin.feedbackme.controller;

import lombok.RequiredArgsConstructor;
import org.katrin.feedbackme.dto.ReviewDto;
import org.katrin.feedbackme.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ReviewDto getById(@PathVariable long id) {
        return reviewService.getById(id);
    }

    @GetMapping
    public List<ReviewDto> getAll() {
        return reviewService.getAll();
    }

    @PutMapping
    public ReviewDto update(@RequestBody ReviewDto reviewDto) {
        return reviewService.update(reviewDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        reviewService.deleteReview(id);
    }
}
