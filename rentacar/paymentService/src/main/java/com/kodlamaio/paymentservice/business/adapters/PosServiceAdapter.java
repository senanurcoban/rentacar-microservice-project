package com.kodlamaio.paymentservice.business.adapters;

import org.springframework.stereotype.Service;

@Service
public class PosServiceAdapter implements PosCheckService{

	@Override
	public boolean pay() {
	
		return true;
	}

}
