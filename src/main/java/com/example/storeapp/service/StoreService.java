package com.example.storeapp.service;

import com.example.storeapp.model.OrderInfo;
import com.example.storeapp.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoreService {
    @Autowired
    StoreRepo storeRepo;

    public Optional<OrderInfo> getStatus(int orderId){
        return this.storeRepo.findById(orderId);
    }
}
