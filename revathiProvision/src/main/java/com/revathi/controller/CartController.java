package com.revathi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.engine.config.internal.ConfigurationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revathi.service.ConfirmServiceImpl;
import com.revathi.model.WishList;
import com.revathi.service.ProductServiceImpl;
import com.revathi.service.UserServiceImpl;
import com.revathi.service.WishlishServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	  @Autowired
		private UserServiceImpl userimpl;
	    
	    @Autowired
	    private ProductServiceImpl proimpl;
	    
	    @Autowired
	    private ConfirmServiceImpl coimpl;
	    
	    @Autowired
	    private WishlishServiceImpl wishimpl;
	    
	    long inv =100000;
	    @GetMapping(value= "/buy-{product_id}-{userId}-product")
	    public ResponseEntity<?> addProductCart(@PathVariable("userId") int userid,@PathVariable("product_id") int product_id){
	    	System.out.println("im buy product..."+userid);
	    	if(userid!=0) {
				 String add= wishimpl.addItem(product_id,userid);
				 if(add.equals("done")) {
					 Map mp = new HashMap();
					 mp.put("status",200);
					 mp.put("msg","done");
					 System.out.println("done");
					  return ResponseEntity.ok(mp); 
					 }
			 }
			 return (ResponseEntity<?>) ResponseEntity.internalServerError();
	    }
	    
		 @GetMapping(value= "/wishlist-{userId}")
		 public ResponseEntity<?> showWishList(@PathVariable("userId") int userid) {
				double Netamout=0;
				System.out.println("im cart");
				if(userid!=0) {
					List<WishList> wishList=wishimpl.getWishList((int)userid,0);
//				for(int i=0;i<wishList.size();i++) {
//					Netamout+= wishList.get(i).getAmount();
	//
//				}
//				System.out.println(Netamout);
//				model.addAttribute("netamount", Netamout);
//				model.addAttribute("wishList",wishList);
//				model.addAttribute("userId",userid);	

				return ResponseEntity.ok(wishList); 
			 }
			 else {return (ResponseEntity<?>) ResponseEntity.internalServerError();}
			}
		 
		 @GetMapping(value= "/dec-{productId}-{userId}")
			public  ResponseEntity<?> decrementQty(@PathVariable("productId") int productId,@PathVariable("userId") int user_id) {
				if(user_id!=0) {
					WishList wh=wishimpl.updateItem(productId, user_id, productId, 1);
					return ResponseEntity.ok("Done"); 

				}
				else {
					return (ResponseEntity<?>) ResponseEntity.internalServerError();
				}
			}
		 @GetMapping(value= "/delete-{product_id}-{userId}-list")
			public  ResponseEntity<?> deleteProductCart(ModelMap model,@PathVariable("product_id") int product_id,@PathVariable("userId") int userid) {
				 if(userid!=0) {
//					 System.out.println(product_id+"   ..>"+userid);
					 wishimpl.deleteItem(userid, product_id);
						return ResponseEntity.ok("Done"); 
				 }
				 else {
						return (ResponseEntity<?>) ResponseEntity.internalServerError();
					}
			
			}
		 
		 @GetMapping(value = "/cofirm-{userId}-order") 
		  public ResponseEntity<?> confirmOrder(@PathVariable("userId") int userId) {
			 System.out.println("Im confirm order......");
				double Netamout=0;
				int qty=0;
				 if(userId!=0) {
					 ArrayList<WishList> wishList=(ArrayList<WishList>) wishimpl.getWishList((int)userId,0);
						for(int i=0;i<wishList.size();i++) {
							Netamout+= wishList.get(i).getAmount();
							qty+=wishList.get(i).getQty();
							long pid=wishList.get(i).getProductId();
							int qt =proimpl.getProductById((int) pid).getQty();
							int wqt=wishList.get(i).getQty();
							proimpl.updateQty((qt-wqt),(int)pid);
						}
						if(wishList.isEmpty()) {
							return ResponseEntity.ok("Empty");
						}
						else {
//						System.out.println(wishList.size()+"....."+inv);
						System.out.println("Im called..in order");
						coimpl.addOrder(qty,Netamout , (int)userId,wishList,"0",inv);
						wishimpl.delFromCart(userId);
						++inv;

						qty=0;Netamout=0;
						return ResponseEntity.ok("Done");
						}
						
						
				 }
				 else {
						return (ResponseEntity<?>) ResponseEntity.internalServerError();
				 }
		 }
}
