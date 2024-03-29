package com.kodlamaio.InventoryService.business.requests.create;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

	@Min(0)
	private double dailyPrice;
	@Min(2015)
	private int modelYear;

	@NotBlank
	private String plate;
	
	@Min(1)
	@Max(3)
	private int state;
	
	@NotBlank
	@NotNull
	private String modelId;
	
}
