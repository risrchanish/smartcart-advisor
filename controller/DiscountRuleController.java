package risrchanish.product.recommend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import risrchanish.product.recommend.dto.discountrule.DiscountRuleCreateDto;
import risrchanish.product.recommend.dto.discountrule.DiscountRuleResponseDto;
import risrchanish.product.recommend.dto.discountrule.DiscountRuleUpdateDto;
import risrchanish.product.recommend.service.DiscountRuleService;

@RestController
@RequestMapping("discount/api")   // base url: http://localhost:8081/discount/api
public class DiscountRuleController {

	private DiscountRuleService discountService;
	
	DiscountRuleController(DiscountRuleService discountService)
	{
		this.discountService = discountService;
	}
	
	
	//POST API for creating discount rule
	@PostMapping
	ResponseEntity<DiscountRuleResponseDto>  createDiscountRule(@RequestBody DiscountRuleCreateDto dto)
	{
		DiscountRuleResponseDto createRule = discountService.createDiscountRule(dto);
		
		return ResponseEntity.ok(createRule);
	}
	
	
	// GET API for find rule by ID
	@GetMapping("/{discountId}")    //url: http://localhost:8081/discount/api/1
	ResponseEntity<DiscountRuleResponseDto> getDiscountRuleById(@PathVariable Long discountId)
	{
		DiscountRuleResponseDto getDiscountById = discountService.getDiscountRuleById(discountId);
		
		return ResponseEntity.ok(getDiscountById);
	}
	
	
	// Find all discount rules
	
	@GetMapping("/all")		// http://localhost:8081/discount/api/all
	ResponseEntity<Page<DiscountRuleResponseDto>> getAllDiscountRules(Pageable pageable)
	{
		Page<DiscountRuleResponseDto> getAllDiscounts = discountService.getAllDiscountRules(pageable);
		
		return ResponseEntity.ok(getAllDiscounts);
	}
	
	
	// Get rules by category
	
	@GetMapping("/category")		// http://localhost:8081/discount/api/category?category={value}
	ResponseEntity<Page<DiscountRuleResponseDto>> getDiscountRulesByCategory(
														@RequestParam String category, 
														Pageable pageable)
	{
		
		Page<DiscountRuleResponseDto> getRulesByCategory = discountService
											.getDiscountRulesByCategory(category, pageable);
		
		return ResponseEntity.ok(getRulesByCategory);
	}
	
	
	// get discount rule by tag
	
	@GetMapping("/tag")			//http://localhost:8081/discount/api/tag?tag={vale}
	ResponseEntity<Page<DiscountRuleResponseDto>> getDiscountRulesByTag( 
																@RequestParam String tag, 
																Pageable pageable)
	{
		Page<DiscountRuleResponseDto> getRulesByTag = discountService
										.getDiscountRulesByTag(tag, pageable);
		
		return ResponseEntity.ok(getRulesByTag);
	}
	
	
	
	@GetMapping("/discount/product/id") 	// //http://localhost:8081/discount/api/discount/product/id?productId={value}
	ResponseEntity<Page<DiscountRuleResponseDto>> getDiscountRulesByProductId(
														@RequestParam(name="productId") Long productId, Pageable pageable)
	{
		Page<DiscountRuleResponseDto> getRulesByProductId = discountService.getDiscountRulesByProductId(productId, pageable);
		
		return ResponseEntity.ok(getRulesByProductId);
	}
	
	
	// Update a discount
	
	@PutMapping("/update/{discountId}")  	//	url: http://localhost:8081/discount/api/update/{value}
	ResponseEntity<DiscountRuleResponseDto> updateDiscountRule(@PathVariable Long discountId, DiscountRuleUpdateDto dto)
	{
		DiscountRuleResponseDto updateDiscount = discountService.updateDiscountRule(discountId, dto);
		
		return ResponseEntity.ok(updateDiscount);
	}
	
	
	
	// Get discount by Product
	/*
	@GetMapping("/discount/product/{productId}")			// http://localhost:8081/discount/api/discount/product/{value}
	ResponseEntity<Double> applyDiscountByProductId(@PathVariable Long productId)
	{
		Double ruleByProduct = discountService.applyDiscountByProduct(productId);
		
		return ResponseEntity.ok(ruleByProduct);
	}
	*/
	
	@GetMapping("/discount/product")			//  http://localhost:8081/discount/api/discount/product?productId=1
	public ResponseEntity<Double> applyDiscountByProductId(@RequestParam(name = "productId") Long productId) {
	    Double ruleByProduct = discountService.applyDiscountByProduct(productId);
	    return ResponseEntity.ok(ruleByProduct);
	}
	
	// delete discount rule
	@DeleteMapping("/delete/{discountId}")
	ResponseEntity<String> deleteDiscountRule(@PathVariable Long discountId)
	{
		discountService.deleteDiscountRule(discountId);
		
		return ResponseEntity.ok("Discount rule with Id: "+discountId+" has been deleted sucessfully!");
	}
	
}
