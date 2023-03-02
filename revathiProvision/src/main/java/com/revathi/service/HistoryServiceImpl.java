package com.revathi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revathi.model.OrderHistory;
import com.revathi.repository.OrderHistoryRepository;

@Service
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	OrderHistoryRepository hisrep;
	
	public HistoryServiceImpl(OrderHistoryRepository hisrep){
		this.hisrep=hisrep;
	}

	@Override
	public int saverOrderHis(OrderHistory his) {
		// TODO Auto-generated method stub
		hisrep.save(his);
		return 1;
	}

	@Override
	public OrderHistory findById(int userId) {
		// TODO Auto-generated method stub
		Optional<OrderHistory> h=hisrep.findById(userId);
		if(h.isPresent()) {
			return h.get();
			}
		return null ;
	}
}
