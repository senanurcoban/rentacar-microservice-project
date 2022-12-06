package com.kodlamaio.rentalService.api;

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

import com.kodlamaio.common.requests.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetAllRentalsResponse;
import com.kodlamaio.rentalService.business.responses.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.UpdateRentalResponse;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalsController {

	private RentalService rentalService;
	
	@GetMapping
	public List<GetAllRentalsResponse> getAll(){
		return this.rentalService.getAll();
	}
	@PostMapping
	public CreateRentalResponse add(@Valid @RequestBody CreateRentalRequest createRentalRequest,CreatePaymentRequest createPaymentRequest) {
		return this.rentalService.add(createRentalRequest,createPaymentRequest);
	}
	
	@PutMapping
	public UpdateRentalResponse update(@Valid @RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
	     rentalService.delete(id);
	}
	@GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable String id) {
        return  rentalService.getById(id);
    }
	@GetMapping("/totalpricebyid/{id}")
	public double getTotalPrice(@PathVariable String id) {
		return rentalService.getTotalPrice(id);
	}
}
