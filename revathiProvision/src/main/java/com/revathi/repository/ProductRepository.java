package com.revathi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revathi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
	 @Transactional
	 @Modifying
	 @Query("update Product u set u.qty = :qty where u.product_id = :product_id")
	 void updateQty(@Param(value = "qty") int qty, @Param(value = "product_id") int product_id);

}
