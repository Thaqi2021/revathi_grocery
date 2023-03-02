package com.revathi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.revathi.model.Product;

public interface ProductService {
	List<Product> showProduct();
	Product AddProduct(Product product,MultipartFile ImageFile);
	void deleteProduct(int productId);
	Product getProductById(int productId);
	Product updateProduct(Product user, int productid,MultipartFile ImageFile);
	void updateQty(int qty, int productid);

	
}
