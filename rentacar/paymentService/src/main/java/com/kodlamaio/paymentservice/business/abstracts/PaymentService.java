package com.kodlamaio.paymentservice.business.abstracts;

import com.kodlamaio.common.rentalPayment.PayMoneyRequest;
import com.kodlamaio.paymentservice.business.responses.CreatePaymentResponse;

public interface PaymentService {

	
	CreatePaymentResponse add(PayMoneyRequest createPaymentRequest);
	
	
}

