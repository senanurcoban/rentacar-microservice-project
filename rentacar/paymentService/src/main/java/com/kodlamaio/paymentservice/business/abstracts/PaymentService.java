package com.kodlamaio.paymentservice.business.abstracts;
import com.kodlamaio.common.requests.CreatePaymentRequest;

public interface PaymentService {
	void add(CreatePaymentRequest createPaymentRequest);
	void delete(String id);
	void updateStatus(String id, int status);
	
	
	
}

