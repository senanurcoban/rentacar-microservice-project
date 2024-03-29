package com.kodlamaio.InventoryService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.kodlamaio.InventoryService.business.abstracts.CarService;
import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.common.events.rental.RentalUpdatedEvent;

@Service
public class RentalConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
	private CarService carService;
	
    @KafkaListener( topics = "${spring.kafka.topic.name}",groupId = "create")
    public void consume(RentalCreatedEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        carService.updateCarState(event.getCarId(), 3);
        // save the order event into the database
    }
	
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "update")
	public void consume(RentalUpdatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		carService.updateCarState(event.getOldCarId(), 1);
		carService.updateCarState(event.getNewCarId(), 3);
		// save the order event into the database
	}
}
