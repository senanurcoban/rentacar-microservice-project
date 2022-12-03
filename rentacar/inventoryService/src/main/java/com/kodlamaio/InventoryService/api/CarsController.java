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

import com.kodlamaio.InventoryService.business.abstracts.CarService;
import com.kodlamaio.InventoryService.business.requests.create.CreateCarRequest;
import com.kodlamaio.InventoryService.business.requests.update.UpdateCarRequest;
import com.kodlamaio.InventoryService.business.responses.create.CreateCarResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.InventoryService.business.responses.get.GetCarResponse;
import com.kodlamaio.InventoryService.business.responses.update.UpdateCarResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
	
	private CarService carService;
	
	@GetMapping
	public List<GetAllCarsResponse> getAll(){
		return this.carService.getAll();
	}
	@GetMapping("/{id}")
	public GetCarResponse getById (@PathVariable String id) {
		return this.carService.getById(id);
	}
	@PostMapping
	public CreateCarResponse add(@Valid @RequestBody CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
	}
	@PutMapping
	public UpdateCarResponse update(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		 this.carService.delete(id);
	}
	@GetMapping("/checkIfCarAvailable/{id}")
    public void checkIfCarAvailable(@PathVariable String id) {
        carService.checkIfCarAvailable(id);
    }
}
