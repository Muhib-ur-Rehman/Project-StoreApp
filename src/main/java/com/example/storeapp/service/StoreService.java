package com.example.storeapp.service;

import com.example.storeapp.model.OrderInfo;
import com.example.storeapp.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    StoreRepo storeRepo;

    public StoreService(StoreRepo storeRepo){
        this.storeRepo=storeRepo;
    }

    public OrderInfo getStatus(int orderId){
        return this.storeRepo.findById(orderId).get();
    }
}
