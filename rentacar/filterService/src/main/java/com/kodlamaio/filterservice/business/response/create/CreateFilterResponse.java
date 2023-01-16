package com.kodlamaio.filterservice.business.response.create;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFilterResponse {

	private String id;
	private String carId;
	private String brandId;
	private String brandName;
	private String modelId;
	private String modelName;
	private double dailyPrice;
	private int modelYear;
	private String plate;
	private int state;
}
