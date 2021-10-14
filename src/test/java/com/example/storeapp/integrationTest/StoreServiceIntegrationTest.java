package com.example.storeapp.integrationTest;

import com.example.storeapp.model.OrderInfo;
import com.example.storeapp.repository.StoreRepo;
import com.example.storeapp.service.StoreService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class StoreServiceIntegrationTest {
    @Autowired
    StoreRepo storeRepo;

    @Test
    public void getStatusIntegrationTest(){
        StoreService storeService = new StoreService(storeRepo);
        OrderInfo order=storeService.getStatus(15);
        Assert.assertEquals(15,order.getOrderId());
        Assert.assertEquals("chicken Burger",order.getName());
        Assert.assertEquals(6,order.getQty());
        Assert.assertEquals("ACCEPTED",order.getPaymentStatus());
    }
}
