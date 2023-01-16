package com.kodlamaio.rentalService.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.common.events.rental.RentalUpdatedEvent;
import com.kodlamaio.common.requests.CreatePaymentRequest;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentalService.api.PaymentApi;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetAllRentalsResponse;
import com.kodlamaio.rentalService.business.responses.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.UpdateRentalResponse;
import com.kodlamaio.rentalService.client.CarClient;
import com.kodlamaio.rentalService.dataAccess.RentalRepository;
import com.kodlamaio.rentalService.entities.Rental;
import com.kodlamaio.rentalService.kafka.RentalProducer;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private RentalProducer rentalProducer;
	private CarClient carClient;
	private PaymentApi paymentApi;
	@Override
	public List<GetAllRentalsResponse> getAll() {
		List<Rental> rentals=this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = 
				rentals.stream().map(rental->this.modelMapperService.forResponse()
						.map(rental, GetAllRentalsResponse.class)).collect(Collectors.toList());
		
		return response;
		
	}

	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest,CreatePaymentRequest createPaymentRequest) {
		carClient.checkIfCarAvailable(createRentalRequest.getCarId());
		Rental rental= this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(UUID.randomUUID().toString());   
		rental.setDateStarted(LocalDateTime.now());
		double totalPrice=createRentalRequest.getDailyPrice()*createRentalRequest.getRentedForDays();
		rental.setTotalPrice(totalPrice);
		
		paymentApi.add(createPaymentRequest);
		
		Rental rentalCreated=this.rentalRepository.save(rental);
		RentalCreatedEvent rentalCreatedEvent=new RentalCreatedEvent();
		rentalCreatedEvent.setCarId(rentalCreated.getCarId());
		rentalCreatedEvent.setMessage("Rental Created");
		
		this.rentalProducer.sendMessage(rentalCreatedEvent);
		
		CreateRentalResponse createRentalResponse = this.modelMapperService.forResponse().map(rental,CreateRentalResponse.class);
		return createRentalResponse;
		
		
		
	}

	@Override
	public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {
		checkIfRentalExists(updateRentalRequest.getId());
		carClient.checkIfCarAvailable(updateRentalRequest.getCarId());
        RentalUpdatedEvent rentalUpdatedEvent = new RentalUpdatedEvent();
		
		Rental rental = this.rentalRepository.findById(updateRentalRequest.getId()).get();
		rentalUpdatedEvent.setOldCarId(rental.getCarId());
		
		//this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rental.setCarId(updateRentalRequest.getCarId());
		rental.setDailyPrice(updateRentalRequest.getDailyPrice());
		rental.setRentedForDays(updateRentalRequest.getRentedForDays());
		rental.setDateStarted(LocalDateTime.now());
		double totalPrice = updateRentalRequest.getDailyPrice() * updateRentalRequest.getRentedForDays();
		rental.setTotalPrice(totalPrice);

		Rental rentalUpdated = this.rentalRepository.save(rental);
		
		rentalUpdatedEvent.setNewCarId(rentalUpdated.getCarId());
		rentalUpdatedEvent.setMessage("Rental Updated");
		
		this.rentalProducer.sendMessage(rentalUpdatedEvent);
		
		UpdateRentalResponse updateRentalResponse = this.modelMapperService.forResponse().map(rental, UpdateRentalResponse.class);
		
		return updateRentalResponse;
		
	}

	@Override
	public void delete(String id) {
		checkIfRentalExists(id);
        rentalRepository.deleteById(id);
		
	}

	@Override
	public GetRentalResponse getById(String id) {
		    checkIfRentalExists(id);
	        Rental rental = rentalRepository.findById(id).get();
	        GetRentalResponse data =  modelMapperService.forResponse().map(rental, GetRentalResponse.class);
	        return data;
		
	}

	public void checkIfRentalExists(String id) {
		if (!rentalRepository.existsById(id)) {
            throw new BusinessException("Rental not found");
        }
	}

	@Override
	public void setConditionByPayment(String id) {
		Rental rental = this.rentalRepository.findById(id).get();
		if (rental.getCondition()==1) {
			rental.setCondition(2);	
		}
		rentalRepository.save(rental);
		
	}
	@Override
	public double getTotalPrice(String id) {
		return rentalRepository.findById(id).get().getTotalPrice();
	}
}
