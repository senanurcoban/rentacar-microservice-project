package com.kodlamaio.paymentservice.business.requests;

import java.time.LocalDate;

public class CreatePaymentRequest {

	private String rentalId;
	private String cardNo;
	private String cardHolder;
	private int cvv;
	private LocalDate carDate;
}
