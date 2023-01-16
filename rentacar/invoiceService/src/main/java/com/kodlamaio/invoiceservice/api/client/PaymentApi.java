package com.kodlamaio.invoiceservice.api.client;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "paymentApi", url = "http://localhost:9011/payment/api/payments/")
public interface PaymentApi {

	
}
