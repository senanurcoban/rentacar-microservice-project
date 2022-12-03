package com.kodlamaio.rentalService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;

@FeignClient(value="carclient",url="http://localhost:9011/")
public interface CarClient {

	@RequestMapping(method=RequestMethod.GET,value="stock/api/cars/checkIfCarAvailable/{id}")
	@Headers(value="Content-Type: application/json")
	void checkIfCarAvailable(@PathVariable String id);

	
}
