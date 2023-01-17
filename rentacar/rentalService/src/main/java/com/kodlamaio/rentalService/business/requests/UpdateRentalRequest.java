package com.kodlamaio.rentalService.business.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {
	@NotNull
	private String id;
	
	@NotNull
	private String carId;
	
	@NotNull
	@Min(value = 1)
	private int rentedForDays;
	
	@NotNull
	@Min(0)
	private double dailyPrice;
	
	
}
