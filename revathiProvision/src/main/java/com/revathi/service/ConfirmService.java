package com.revathi.service;

import java.util.ArrayList;
import java.util.List;

import com.revathi.model.ConfirmOrder;
import com.revathi.model.WishList;

public interface ConfirmService {
	ConfirmOrder addOrder(int qty,double amount,int cid ,ArrayList<WishList> list,String payment_id,long invoiceNo);
	List<ConfirmOrder> getAllOrder(int ccflag);
	
	void updafeccflag(int cid,int ccflag,String invoiceId);
}
