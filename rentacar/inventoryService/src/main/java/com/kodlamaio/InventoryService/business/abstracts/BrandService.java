package com.kodlamaio.InventoryService.business.abstracts;
import java.util.List;
import com.kodlamaio.InventoryService.business.requests.create.CreateBrandRequest;
import com.kodlamaio.InventoryService.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.InventoryService.business.responses.create.CreateBrandResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetBrandResponse;
import com.kodlamaio.InventoryService.business.responses.update.UpdateBrandResponse;

public interface BrandService {

	List<GetAllBrandsResponse> getAll();
	CreateBrandResponse add(CreateBrandRequest createBrandRequest);
	UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest);
	GetBrandResponse getById(String id);
	void delete(String id);
	
	
}
