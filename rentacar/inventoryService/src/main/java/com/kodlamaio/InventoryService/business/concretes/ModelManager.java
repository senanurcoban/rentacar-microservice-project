package com.kodlamaio.InventoryService.business.concretes;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.kodlamaio.InventoryService.business.abstracts.BrandService;
import com.kodlamaio.InventoryService.business.abstracts.ModelService;
import com.kodlamaio.InventoryService.business.requests.create.CreateModelRequest;
import com.kodlamaio.InventoryService.business.requests.update.UpdateModelRequest;
import com.kodlamaio.InventoryService.business.responses.create.CreateModelResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetModelResponse;
import com.kodlamaio.InventoryService.business.responses.update.UpdateModelResponse;
import com.kodlamaio.InventoryService.dataAccess.ModelRepository;
import com.kodlamaio.InventoryService.entities.Model;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {

	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	private BrandService brandService;
	@Override
	public List<GetAllModelsResponse> getAll() {
		List<Model> models= modelRepository.findAll();
		List<GetAllModelsResponse> response = 
				models.stream().map(brand->this.modelMapperService.forResponse()
						.map(brand, GetAllModelsResponse.class)).collect(Collectors.toList());
		return response;
		
	}

	@Override
	public CreateModelResponse add(CreateModelRequest createModelRequest) {
		checkIfModelExistsByName(createModelRequest.getName());
		this.brandService.checkIfBrandExistsByBrandId(createModelRequest.getBrandId());
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());
		this.modelRepository.save(model);
		

		CreateModelResponse createModelResponse = this.modelMapperService.forResponse().map(model,
				CreateModelResponse.class);
		return createModelResponse;
	}
	
		
	

	@Override
	public UpdateModelResponse update(UpdateModelRequest updateModelRequest) {
		checkIfModelExistsById(updateModelRequest.getId());
		//checkIfModelExistsByName(updateModelRequest.getName());
		this.brandService.checkIfBrandExistsByBrandId(updateModelRequest.getBrandId());
		 Model model = modelMapperService.forRequest().map(updateModelRequest,Model.class);
		 modelRepository.save(model);
		 UpdateModelResponse response=modelMapperService.forResponse().map(model,UpdateModelResponse.class);
		 return response;
		
	}


	@Override
	public GetModelResponse getById(String id) {
		checkIfModelExistsById(id);
		Model model=this.modelRepository.findById(id).get();
		GetModelResponse response=this.modelMapperService.forResponse().map(model,GetModelResponse.class);
		return response;
		
	}

	@Override
	public void delete(String id) {
		checkIfModelExistsById(id);
		this.modelRepository.deleteById(id);
		
	}

	private void checkIfModelExistsById(String id) {
		Object result=this.modelRepository.findById(id).orElse(null);
		if(result==null) {
			throw new BusinessException("MODEL NO.EXISTS");
		}
	}
	private void checkIfModelExistsByName(String name) {
		Object result= this.modelRepository.findByName(name);
		if (result != null) {
			throw new BusinessException("MODEL.EXISTS");
		}
	}
	
	
	/*private void checkIfBrandExistsByBrandId(String id) {
		brandService.getById(id);
	}*/
	
}
