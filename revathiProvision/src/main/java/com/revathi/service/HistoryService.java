package com.revathi.service;

import com.revathi.model.OrderHistory;

public interface HistoryService {
	public int saverOrderHis(OrderHistory his);
	public OrderHistory findById(int userId);
	
	
}
