package com.kodlamaio.InventoryService.api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kodlamaio.InventoryService.business.abstracts.BrandService;
import com.kodlamaio.InventoryService.business.requests.create.CreateBrandRequest;
import com.kodlamaio.InventoryService.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.InventoryService.business.responses.create.CreateBrandResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetBrandResponse;
import com.kodlamaio.InventoryService.business.responses.update.UpdateBrandResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandsController {

	private BrandService brandService;
	
	@GetMapping
	public List<GetAllBrandsResponse> getAll(){
		return this.brandService.getAll();
	}
	@GetMapping("/{id}")
	public GetBrandResponse getById (@PathVariable String id) {
		return this.brandService.getById(id);
	}
	@PostMapping
	public CreateBrandResponse add(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}
	@PutMapping
	public UpdateBrandResponse update(@Valid @RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		 this.brandService.delete(id);
	}
}
