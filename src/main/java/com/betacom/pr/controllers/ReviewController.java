package com.betacom.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.betacom.pr.dto.inputs.ReviewReq;
import com.betacom.pr.dto.outputs.ReviewDTO;
import com.betacom.pr.models.Review;
import com.betacom.pr.repositories.IReviewRepository;

import java.util.List;

@RestController
@RequestMapping("/rest/reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    @Autowired
    private IReviewRepository revR;

    @GetMapping("/product/{productId}")
    public List<ReviewDTO> getByProduct(@PathVariable Long productId) {
        return revR.findByProductId(productId).stream().map(saved -> {
            ReviewDTO dto = new ReviewDTO();
            dto.setId(saved.getId());
            dto.setUserName(saved.getUserName());
            dto.setComment(saved.getComment());
            dto.setRating(saved.getRating());
            dto.setProductId(saved.getProductId());
            return dto;
        }).toList();
    }

    @PostMapping
    public ReviewDTO create(@RequestBody ReviewReq req) {
        Review entity = new Review();
        entity.setProductId(req.getProductId());
        entity.setRating(req.getRating());
        entity.setComment(req.getComment());
        entity.setUserName(req.getUserName()); 

        Review saved = revR.save(entity);
        
        return convertToDTO(saved);
    }

    @PutMapping("/{id}")
    public ReviewDTO update(@PathVariable Long id, @RequestBody ReviewReq req) {
        Review review = revR.findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found"));
            
        review.setRating(req.getRating());
        review.setComment(req.getComment());
        
        Review saved = revR.save(review);
        return convertToDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        revR.deleteById(id);
    }

    private ReviewDTO convertToDTO(Review saved) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(saved.getId());
        dto.setUserName(saved.getUserName());
        dto.setComment(saved.getComment());
        dto.setRating(saved.getRating());
        dto.setProductId(saved.getProductId());
        return dto;
    }
}