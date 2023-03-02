package com.revathi.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revathi.repository.ConfirmOrderRepository;
import com.revathi.repository.WishListRepository;

import net.sf.jasperreports.engine.JRException;

//import com.revathi.service.invoiceGenerator;
import com.revathi.model.ConfirmOrder;
import com.revathi.model.WishList;
//import com.revathi.service.UserServiceImpl;

@Service
public class ConfirmServiceImpl implements ConfirmService {
	@Autowired
	ConfirmOrderRepository corep;
	
	@Autowired
	WishListRepository werep;
	
	@Autowired
	private WishlishServiceImpl wishimpl;
	  
	  @Autowired
    invoiceGenerator invoice;
	  
	public ConfirmServiceImpl(ConfirmOrderRepository corep) {
		this.corep=corep;
	}
	@Override
	public ConfirmOrder addOrder(int qty, double amount, int cid, ArrayList<WishList> list,
			String payment_id, long invoiceNo) {
		// TODO Auto-generated method stub
		ConfirmOrder order = new ConfirmOrder();
		order.setCid(cid);
		order.setBillDate(LocalDate.now());
		order.setNoOfProduct(qty);
		if(payment_id!="0") {
			order.setPaymentMethod("Online");
		}
		else {
			order.setPaymentMethod("cash");
		}
		System.out.println("im ok");
		order.setPaymentId(payment_id);
		order.setCartProduct(list);
		order.setNetAmount(amount);  //Gst  +((amount*order.getGst())/100)
		order.setInvoiceId("HRC"+invoiceNo);
	    corep.save(order);
	    wishimpl.delFromCart(cid);
	    try {
			invoice.downloadInvoice(list,order.getInvoiceId(),cid);
			
		} catch (JRException e) {e.printStackTrace();} catch (IOException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<ConfirmOrder> getAllOrder(int ccflag) {
		// TODO Auto-generated method stub
		return corep.getAllOrder(0);
	}

	@Override
	public void updafeccflag(int cid, int ccflag, String invoiceId) {
		corep.updateccflag(cid,invoiceId, ccflag );		
	}
	
}
