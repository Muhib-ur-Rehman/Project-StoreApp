package com.example.storeapp.unit_tests;

import com.example.storeapp.model.OrderInfo;
import com.example.storeapp.repository.StoreRepo;
import com.example.storeapp.service.StoreService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class storeServiceUnitTest {

    @Mock
    StoreRepo storeRepo;

    @Test
    public void getStatusUnitTest(){
        StoreService st = new StoreService(storeRepo);
        OrderInfo order = new OrderInfo();
        order.setOrderId(1);
        order.setName("Burger");
        Optional<OrderInfo> optionalOrder = Optional.ofNullable(order);
        Mockito.when(this.storeRepo.findById(1)).thenReturn(optionalOrder);
        Assert.assertEquals(order,st.getStatus(1));
        Assert.assertEquals(order.getOrderId(),st.getStatus(1).getOrderId());
        Assert.assertEquals(order.getName(),st.getStatus(1).getName());
    }
}
