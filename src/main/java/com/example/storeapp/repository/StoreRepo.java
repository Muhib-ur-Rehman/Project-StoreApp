package com.example.storeapp.repository;

import com.example.storeapp.model.OrderInfo;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<OrderInfo,Integer> {
//    public OrderInfo findByOrderId(int orderId);
}
