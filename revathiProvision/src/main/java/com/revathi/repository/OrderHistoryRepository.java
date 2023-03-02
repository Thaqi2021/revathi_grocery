package com.revathi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revathi.model.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Integer>{

}
