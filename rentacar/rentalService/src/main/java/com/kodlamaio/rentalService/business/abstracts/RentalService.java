package com.kodlamaio.rentalService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.requests.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetAllRentalsResponse;
import com.kodlamaio.rentalService.business.responses.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.UpdateRentalResponse;

public interface RentalService {

	List<GetAllRentalsResponse> getAll();
	CreateRentalResponse add(CreateRentalRequest createRentalRequest,CreatePaymentRequest createPaymentRequest);
	UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest);
	void delete(String id);
	GetRentalResponse getById(String id);
	void setConditionByPayment(String id);
	double getTotalPrice(String id);
	
}
