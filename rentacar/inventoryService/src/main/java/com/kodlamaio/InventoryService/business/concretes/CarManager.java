package com.kodlamaio.InventoryService.business.concretes;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.kodlamaio.InventoryService.business.abstracts.CarService;
import com.kodlamaio.InventoryService.business.abstracts.ModelService;
import com.kodlamaio.InventoryService.business.requests.create.CreateCarRequest;
import com.kodlamaio.InventoryService.business.requests.update.UpdateCarRequest;
import com.kodlamaio.InventoryService.business.responses.create.CreateCarResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetCarResponse;
import com.kodlamaio.InventoryService.business.responses.update.UpdateCarResponse;
import com.kodlamaio.InventoryService.dataAccess.CarRepository;
import com.kodlamaio.InventoryService.entities.Car;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CarManager implements CarService{

	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private ModelService modelService;
	
	
	@Override
	public List<GetAllCarsResponse> getAll() {
		List<Car> cars=this.carRepository.findAll();
		List<GetAllCarsResponse> response = 
				cars.stream().map(brand->this.modelMapperService.forResponse()
						.map(brand, GetAllCarsResponse.class)).collect(Collectors.toList());
		
		return response;
		
	}

	@Override
	public CreateCarResponse add(CreateCarRequest createCarRequest) {
		
		checkIfModelExistsByModelId(createCarRequest.getModelId());
		checkIfCarExistsByPlate(createCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());                                        // 16 basamaklı harf sayı olusturur uuıd
		this.carRepository.save(car);
		CreateCarResponse createCarResponse = this.modelMapperService.forResponse().map(car,CreateCarResponse.class);
		return createCarResponse;
		
		
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest updateCarRequest) {
		 checkIfModelExistsByModelId(updateCarRequest.getModelId());
		 checkIfCarExistsById(updateCarRequest.getId());
		 checkIfCarExistsByPlate(updateCarRequest.getPlate());
		 Car car = modelMapperService.forRequest().map(updateCarRequest,Car.class);
		 carRepository.save(car);
		 UpdateCarResponse response=modelMapperService.forResponse().map(car,UpdateCarResponse.class);
		 return response;
	       
	}
	

	@Override
	public void delete(String id) {
		 checkIfCarExistsById(id);
		 this.carRepository.deleteById(id);
		
	}

	@Override
	public GetCarResponse getById(String id) {
		checkIfCarExistsById(id);
		Car car=this.carRepository.findById(id).get();
		GetCarResponse response=this.modelMapperService.forResponse().map(car,GetCarResponse.class);
		return response;
	}
	
	private void checkIfCarExistsById(String id) {
		Car car=this.carRepository.findById(id).orElse(null);
		if(car==null) {
			throw new BusinessException("Car no.EXISTS");
		}
	}
	private void checkIfCarExistsByPlate(String plate) {
		Object result = carRepository.findByPlate(plate);
		if (result != null) {
			throw new BusinessException("CAR.EXISTS");
		}
	}
	
	private void checkIfModelExistsByModelId(String modelId) {
		Object result = modelService.getById(modelId);
		if (result == null) {
			throw new BusinessException("MODEL.NO.EXİSTS");
		}
	}

	@Override
	public void updateCarState(String carId,int state) {
		Car car = carRepository.findById(carId).get();
		car.setState(2);
		carRepository.save(car);
	}

	@Override
	public void checkIfCarAvailable(String id) {
		Car car=this.carRepository.findById(id).get();
		if(car.getState()!=1) {
			throw new BusinessException("Car not available");
		}
		
	}
	
	
	
	
	


}
