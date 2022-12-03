package com.kodlamaio.paymentservice.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.requests.UpdatePaymentRequest;
import com.kodlamaio.paymentservice.business.responses.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.responses.GetAllPaymentsResponse;
import com.kodlamaio.paymentservice.business.responses.GetPaymentResponse;
import com.kodlamaio.paymentservice.business.responses.UpdatePaymentResponse;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentsController {

private PaymentService paymentService;
	
	@GetMapping
	public List<GetAllPaymentsResponse> getAll(){
		return this.paymentService.getAll();
	}
	@PostMapping
	public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
		return this.paymentService.add(createPaymentRequest);
	}
	
	@PutMapping
	public UpdatePaymentResponse update(@Valid @RequestBody UpdatePaymentRequest updatePaymentRequest) {
		return this.paymentService.update(updatePaymentRequest);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
	     paymentService.delete(id);
	}
	@GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable String id) {
        return  paymentService.getById(id);
    }
	
}
