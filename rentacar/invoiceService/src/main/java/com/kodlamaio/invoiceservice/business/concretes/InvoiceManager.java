package com.kodlamaio.invoiceservice.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.kodlamaio.common.events.invoice.InvoiceCreatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.requests.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.response.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.response.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.business.response.UpdateInvoiceResponse;
import com.kodlamaio.invoiceservice.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceservice.entities.Invoice;
import com.kodlamaio.invoiceservice.kafka.InvoiceProducer;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
	private InvoiceProducer invoiceProducer;
	@Override
	public List<GetAllInvoicesResponse> getAll() {
		List<Invoice> invoices = invoiceRepository.findAll();
		List<GetAllInvoicesResponse> response = 
				invoices.stream().map(invoice->this.modelMapperService.forResponse()
						.map(invoice, GetAllInvoicesResponse.class)).collect(Collectors.toList());
		
		return response;
       
		
	}
	@Override
	public CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest) {
		Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setId(UUID.randomUUID().toString());
		
		Invoice createdInvoice = invoiceRepository.save(invoice);
		
		InvoiceCreatedEvent invoiceCreatedEvent = new InvoiceCreatedEvent();
		invoiceCreatedEvent.setPaymentId(createdInvoice.getPaymentId());
		invoiceCreatedEvent.setMessage("Payment Created");
		
		this.invoiceProducer.sendMessage(invoiceCreatedEvent);
		
		CreateInvoiceResponse createPaymentResponse = modelMapperService.forResponse().map(invoice, CreateInvoiceResponse.class);
		
		return createPaymentResponse;
	}
	@Override
	public void delete(String id) {
		checkIfInvoiceExists(id);
        invoiceRepository.deleteById(id);
		
	}
	@Override
	public UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest) {
	
		return null;
	}
	@Override
	public GetInvoiceResponse getById(String id) {
		checkIfInvoiceExists(id);
        Invoice invoice = invoiceRepository.findById(id).get();
        GetInvoiceResponse response = modelMapperService.forResponse().map(invoice, GetInvoiceResponse.class);

        return response;
		
	}
	
	private void checkIfInvoiceExists(String id) {
        if (!invoiceRepository.existsById(id)) {
            throw new BusinessException("INVOICE_NOT_FOUND");
        }
    }
	
	
}
