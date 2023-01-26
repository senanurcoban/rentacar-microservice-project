package com.kodlamaio.InventoryService.dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.kodlamaio.InventoryService.entities.dtos.CarDetailDto;

public interface CarDetailRepository extends MongoRepository<CarDetailDto, String>{

}
