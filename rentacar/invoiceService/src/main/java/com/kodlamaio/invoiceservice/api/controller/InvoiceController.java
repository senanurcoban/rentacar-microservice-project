package com.kodlamaio.invoiceservice.api.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.requests.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.response.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.response.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.business.response.UpdateInvoiceResponse;

public class InvoiceController {

private InvoiceService invoiceService;
	

    @GetMapping
    public List<GetAllInvoicesResponse> getAll() {
           return invoiceService.getAll();
    }
	@PostMapping
	public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest) {
		return invoiceService.add(createInvoiceRequest);
	}
	
	@GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable String id) {
        return invoiceService.getById(id);
    }
	
	@PutMapping
	public UpdateInvoiceResponse add(@Valid @RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
		return invoiceService.update(updateInvoiceRequest);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
	        invoiceService.delete(id);
	}

}
