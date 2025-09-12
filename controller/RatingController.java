package risrchanish.product.recommend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import risrchanish.product.recommend.dto.rating.RatingCreateDto;
import risrchanish.product.recommend.dto.rating.RatingResponseDto;
import risrchanish.product.recommend.dto.rating.RatingUpdateDto;
import risrchanish.product.recommend.service.RatingService;

@RestController
@RequestMapping("/rating/api")			// base url: http://localhost:8081/rating/api
public class RatingController {

	private RatingService ratingService;
	
	public RatingController(RatingService ratingService)
	{
		this.ratingService = ratingService;
	}
	
	
	// Create rating API
	
	@PostMapping  			// url: http://localhost:8081/rating/api
	ResponseEntity<RatingResponseDto> createRating(@RequestBody @Valid RatingCreateDto dto)
	{
		RatingResponseDto create = ratingService.createRating(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}
	
	
	@GetMapping("/rating/{ratingId}")			// url: http://localhost:8081/rating/api/rating/1
	ResponseEntity<RatingResponseDto> getRatingById(@PathVariable Long ratingId)
	{
		RatingResponseDto ratingById = ratingService.getRatingById(ratingId);
		
		return ResponseEntity.ok(ratingById);
	}
	
	
	// Get rating by product ID
	@GetMapping("/product/{productId}")  // url: http://localhost:8081/rating/api/product/1
	ResponseEntity<Page<RatingResponseDto>> getRatingsByProduct(@PathVariable Long productId, Pageable pageable)
	{
		Page<RatingResponseDto> ratingByProduct = ratingService.getRatingsByProduct(productId, pageable);
		
		return ResponseEntity.ok(ratingByProduct);
	}
	
	// Get rating by user Id
	
	@GetMapping("/user/{userId}")		// url: http://localhost:8081/rating/api/user/1
	ResponseEntity<Page<RatingResponseDto>> getRatingsByUser(@PathVariable Long userId, Pageable pageable)
	{
		Page<RatingResponseDto> ratingsByUser = ratingService.getRatingsByUser(userId, pageable);
		
		return ResponseEntity.ok(ratingsByUser);
	}
	
	
	@GetMapping("/all")		// url: http://localhost:8081/rating/api/all
	ResponseEntity<Page<RatingResponseDto>> getAllRatings(Pageable pageable)
	{
		Page<RatingResponseDto> allRatings = ratingService.getAllRatings(pageable);
		
		return ResponseEntity.ok(allRatings);
	}
	
	
	// Update API 
	
	@PutMapping("/update/rating")		// url: http://localhost:8081/rating/api/update/rating?productId={v1}&userId={v2}
	ResponseEntity<RatingResponseDto> updateRating(@RequestParam Long productId, 
												  @RequestParam Long userId, 
												  @RequestBody @Valid RatingUpdateDto dto)
	{
		RatingResponseDto updatedRating = ratingService.updateRating(productId, userId, dto);
		
		return ResponseEntity.ok(updatedRating);
	}
	
	
	// Delete API
	
	@DeleteMapping("/delete/rating")	// url: http://localhost:8081/rating/api/delete/rating?ratingId={}&userId={}&productId{}
	ResponseEntity<String> deleteRating(@RequestParam Long ratingId, 
										@RequestParam Long userId, 
										@RequestParam Long productId)
	{
		ratingService.deleteRating(ratingId, userId, productId);
		
		return ResponseEntity.ok("Rating having Id: "+ratingId+" User Id: "+userId+" Product Id: "+productId+" has been deleted sucessfully!");
	}
	
}
