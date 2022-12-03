package com.kodlamaio.paymentservice.business.abstracts;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.kodlamaio.paymentservice.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.requests.UpdatePaymentRequest;
import com.kodlamaio.paymentservice.business.responses.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.responses.GetAllPaymentsResponse;
import com.kodlamaio.paymentservice.business.responses.GetPaymentResponse;
import com.kodlamaio.paymentservice.business.responses.UpdatePaymentResponse;

public interface PaymentService {

	List<GetAllPaymentsResponse> getAll();
	CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest);
	UpdatePaymentResponse update(UpdatePaymentRequest updatePaymentRequest);
	void getTotalPrice(@PathVariable String id);
	void delete(String id);
	GetPaymentResponse getById(String id);
	
}

