package com.kodlamaio.paymentservice.api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentsController {

     private PaymentService paymentService;

/*@PostMapping
public void add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
	paymentService.add(createPaymentRequest);
}*/
	
	

	
	
}
