package com.kodlamaio.InventoryService.business.abstracts;
import java.util.List;
import com.kodlamaio.InventoryService.business.requests.create.CreateModelRequest;
import com.kodlamaio.InventoryService.business.requests.update.UpdateModelRequest;
import com.kodlamaio.InventoryService.business.responses.create.CreateModelResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetModelResponse;
import com.kodlamaio.InventoryService.business.responses.update.UpdateModelResponse;

public interface ModelService {

	List<GetAllModelsResponse> getAll();
	CreateModelResponse add(CreateModelRequest createModelRequest);
	UpdateModelResponse update(UpdateModelRequest updateModelRequest);
	GetModelResponse getById(String id);
	void delete(String id);
}
