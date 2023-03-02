package com.revathi.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revathi.model.User;
import com.revathi.model.Product;
import com.revathi.model.WishList;
import com.revathi.repository.WishListRepository;
import com.revathi.service.ProductServiceImpl;
import com.revathi.service.UserServiceImpl;

@Service
public class WishlishServiceImpl implements WishListService ,Serializable{
	@Autowired
	WishListRepository listrepo;
	
	@Autowired
	ProductServiceImpl proimpl;
	
	@Autowired
	UserServiceImpl userimpl;
	
	Date dt = new Date();
	User user;
	WishList itemlist = new WishList();
	int i=1;

	WishlishServiceImpl(WishListRepository listrepo){
		this.listrepo=listrepo;
	}
	@Override
	public String addItem(long product_id, long user_id) {
		Product product =	proimpl.getProductById((int)product_id);
//		System.out.println(product.getBrand()+"............."+user_id);
		User user =userimpl.getUserById(user_id);
		// TODO Auto-generated method stub
		if(user.getCust_id()!=0) {
			WishList checkQty =listrepo.getByproductId((int)user_id, product_id, 0);
			
			if(checkQty!=null) {
				i=checkQty.getQty();
				++i;
				checkQty.setQty(i);
				checkQty.setAmount(product.getPrice()*checkQty.getQty());
				listrepo.save(checkQty);
				return "done";
			}
			else {
		itemlist.setProductId(product.getProduct_id());
		itemlist.setCid((int)(long)user.getCust_id());
		itemlist.setProductName(product.getProductName());
		itemlist.setDate(LocalDate.now());
		itemlist.setQty(1);
		itemlist.setCflag(0);
//		itemlist.setQuno(product.getHsncode()+"/"+user.getId());
		itemlist.setAmount(product.getPrice()*itemlist.getQty());
//		System.out.println(product.getBrand()+".............");
		listrepo.save(itemlist);
		itemlist=new WishList();
		return "done";
			}
		}
		return "No";
	}

	@Override
	public WishList updateItem(long product_id, int cid, int cflag, int di) {
		WishList checkQty =listrepo.getByproductId(cid, product_id, 0);
		if(checkQty!=null) {
			double price =(checkQty.getAmount()/checkQty.getQty());
			if(di==0 ) {
			i=checkQty.getQty();
			++i;
			checkQty.setQty(i);
			checkQty.setAmount(price*checkQty.getQty());
			listrepo.save(checkQty);
			}
			else if(di==1) {
				if(checkQty.getQty()==1) {
					deleteItem((int)checkQty.getCid(),(int)checkQty.getProductId());
				}
				else if(checkQty.getQty()>0) {
				i=checkQty.getQty();
				--i;
				checkQty.setQty(i);
				checkQty.setAmount(price*checkQty.getQty());
				listrepo.save(checkQty);
				}
			}
		}
		return itemlist;
	}

	@Override
	public void deleteItem(int cid, int productid) {
		// TODO Auto-generated method stub
		listrepo.deleteItem(cid,productid,0);
		
	}

	@Override
	public List<WishList> getWishList(int cid, int cflag) {
		// TODO Auto-generated method stub
		List<WishList> wishList = listrepo.getByCid(cid,cflag);//9790682008
		return wishList;

	}
	@Override
	public void delFromCart(int cid) {
		// TODO Auto-generated method stub
		System.out.println(" im wishlist confirm order...");
		listrepo.deleteProByCustId(cid);
		
	}
	
	
	




	
	
	
}
