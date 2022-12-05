package com.kodlamaio.invoiceservice.business.abstracts;

import java.util.List;

import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.requests.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.response.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.response.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.business.response.UpdateInvoiceResponse;

public interface InvoiceService {

	List<GetAllInvoicesResponse> getAll();
	CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);
	void delete(String id);
	UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest);
	GetInvoiceResponse getById(String id);
}
