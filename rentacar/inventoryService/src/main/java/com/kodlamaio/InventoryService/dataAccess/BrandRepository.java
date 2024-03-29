package com.kodlamaio.InventoryService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kodlamaio.InventoryService.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand,String> {

	Brand findByName(String name);
}
