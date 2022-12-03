package com.kodlamaio.paymentservice.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.requests.UpdatePaymentRequest;
import com.kodlamaio.paymentservice.business.responses.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.responses.GetAllPaymentsResponse;
import com.kodlamaio.paymentservice.business.responses.GetPaymentResponse;
import com.kodlamaio.paymentservice.business.responses.UpdatePaymentResponse;
import com.kodlamaio.paymentservice.dataAccess.PaymentRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService{

	private PaymentRepository paymentRepository;
	private ModelMapperService modelMapperService;
	
	
	@Override
	public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
	
		return null;
	}


	@Override
	public List<GetAllPaymentsResponse> getAll() {
	
		return null;
	}


	@Override
	public UpdatePaymentResponse update(UpdatePaymentRequest updatePaymentRequest) {
		
		return null;
	}


	@Override
	public void getTotalPrice(String id) {
	
		
	}


	@Override
	public void delete(String id) {
		
		
	}


	@Override
	public GetPaymentResponse getById(String id) {
		
		return null;
	}
	
	
}
