package com.kodlamaio.rentalService.business.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.kodlamaio.common.requests.CreatePaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
	@NotNull
	private String carId;
	
	@NotNull
	@Min(value = 1)
	private int rentedForDays;
	
	@NotNull
	@Min(value = 0)
	private double dailyPrice;
	
	private CreatePaymentRequest createPaymentRequest;
	
	
	
}
