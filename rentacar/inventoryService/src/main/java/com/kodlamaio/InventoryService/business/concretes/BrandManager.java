package com.kodlamaio.InventoryService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.kodlamaio.InventoryService.business.abstracts.BrandService;
import com.kodlamaio.InventoryService.business.requests.create.CreateBrandRequest;
import com.kodlamaio.InventoryService.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.InventoryService.business.responses.create.CreateBrandResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetBrandResponse;
import com.kodlamaio.InventoryService.business.responses.update.UpdateBrandResponse;
import com.kodlamaio.InventoryService.dataAccess.BrandRepository;
import com.kodlamaio.InventoryService.entities.Brand;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService{

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	
	@Override
	public List<GetAllBrandsResponse> getAll() {
		List<Brand> brands=this.brandRepository.findAll();
		List<GetAllBrandsResponse> response = 
				brands.stream().map(brand->this.modelMapperService.forResponse()
						.map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
		
		return response;
		
	}

	@Override
	public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistsByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		brand.setId(UUID.randomUUID().toString());
		this.brandRepository.save(brand);
		
		CreateBrandResponse createBrandResponse = this.modelMapperService.forResponse().map(brand, CreateBrandResponse.class);
		return createBrandResponse;
		
	}



	@Override
	public UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest) {
		 checkIfBrandExistsById(updateBrandRequest.getId());
		 checkIfBrandExistsByName(updateBrandRequest.getName());
		 Brand brand = modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
		 brandRepository.save(brand);
		 UpdateBrandResponse response=modelMapperService.forResponse().map(brand,UpdateBrandResponse.class);
		 return response;
		
	}

	@Override
	public GetBrandResponse getById(String id) {
		checkIfBrandExistsById(id);
		Brand brand=this.brandRepository.findById(id).get();
		GetBrandResponse response=this.modelMapperService.forResponse().map(brand,GetBrandResponse.class);
		return response;
		
	}

	@Override
	public void delete(String id) {
		 checkIfBrandExistsById(id);
		 this.brandRepository.deleteById(id);
		
	}
    private void checkIfBrandExistsById(String id) {
    	Brand brand=this.brandRepository.findById(id).orElse(null);
    	if(brand==null) {
    		throw new BusinessException(" Brand does not exist");
    	}
    }

    private void checkIfBrandExistsByName(String name) {
		Brand brand = this.brandRepository.findByName(name);
		if(brand!=null) {
		    throw new BusinessException("BRAND.EXISTS");
	}
   
    
    
    }  
}
