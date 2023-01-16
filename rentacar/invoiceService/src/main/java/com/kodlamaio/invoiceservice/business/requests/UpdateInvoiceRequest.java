package com.kodlamaio.invoiceservice.business.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	@NotNull
	@NotBlank
	private String id;

	@NotNull
	@NotBlank
	private String paymentId;
	
	@NotNull
	@NotBlank
	@Min(3)
	private String customerFirstName;

	@NotNull
	@NotBlank
	private String customerLastName;

	@NotNull
	@NotBlank
	private double tax;

	@NotNull
	@NotBlank
	@Min(0)
	private double totalPrice;

	@NotNull
	@NotBlank
	private String address;
}
