package com.kodlamaio.paymentservice.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.payment.PaymentCreatedEvent;
import com.kodlamaio.common.rentalPayment.PayMoneyRequest;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentservice.api.RentalApi;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.responses.CreatePaymentResponse;
import com.kodlamaio.paymentservice.dataAccess.PaymentRepository;
import com.kodlamaio.paymentservice.entities.Payment;
import com.kodlamaio.paymentservice.kafka.PaymentProducer;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService{
	
	private PaymentRepository paymentRepository;
	private ModelMapperService modelMapperService;
	private PaymentProducer paymentProducer;
	private RentalApi rentalApi;
	@Override
	public CreatePaymentResponse add(PayMoneyRequest createPaymentRequest) {
        checkBalanceEnough(createPaymentRequest.getBalance(),createPaymentRequest.getRentalId());
		
		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		payment.setId(UUID.randomUUID().toString());
		
		Payment createdPayment = paymentRepository.save(payment);
		
		// senkron olduğu için

		PaymentCreatedEvent paymentCreatedEvent = new PaymentCreatedEvent();
		paymentCreatedEvent.setRentalId(createdPayment.getRentalId());
		paymentCreatedEvent.setMessage("Payment Created");
		
		this.paymentProducer.sendMessage(paymentCreatedEvent);
		
		CreatePaymentResponse createPaymentResponse = this.modelMapperService.forResponse().map(payment,
				CreatePaymentResponse.class);
		return createPaymentResponse;
	}

	@Override
	public CreatePaymentResponse delete(String id) {
		paymentRepository.deleteById(id);
		return null;
		
	}

//	@Override
//	public CreatePaymentResponse updateStatus(String id, int status) {
//		Payment payment = this.paymentRepository.findById(id).get();
//		payment.setStatus(status);
//		paymentRepository.save(payment);
//		
//	}

	private void checkBalanceEnough(double balance, String rentalId) {
		if (balance<rentalApi.getTotalPrice(rentalId)) {
			throw new BusinessException("BALANCE.IS.NOT.ENOUGH");
		}
	}
	
	
	

  


	


	


	
		
		
	
	
	
}
