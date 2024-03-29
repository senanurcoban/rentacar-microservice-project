package com.kodlamaio.InventoryService.business.abstracts;
import java.util.List;
import com.kodlamaio.InventoryService.business.requests.create.CreateCarRequest;
import com.kodlamaio.InventoryService.business.requests.update.UpdateCarRequest;
import com.kodlamaio.InventoryService.business.responses.create.CreateCarResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetCarResponse;
import com.kodlamaio.InventoryService.business.responses.update.UpdateCarResponse;

public interface CarService {

	List<GetAllCarsResponse> getAll();
	CreateCarResponse add(CreateCarRequest createCarRequest);
	UpdateCarResponse update(UpdateCarRequest updateCarRequest);
	GetCarResponse getById(String id);
	void delete(String id);
	void updateCarState(String carId,int state);
	void checkIfCarAvailable(String id);
	
}
