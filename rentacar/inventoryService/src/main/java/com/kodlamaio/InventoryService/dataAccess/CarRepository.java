package com.kodlamaio.InventoryService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kodlamaio.InventoryService.entities.Car;

public interface CarRepository extends JpaRepository<Car,String>{
 
	Car findByPlate(String plate);
	
}
