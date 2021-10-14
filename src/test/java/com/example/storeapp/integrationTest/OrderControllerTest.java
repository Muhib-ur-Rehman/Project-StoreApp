package com.example.storeapp.integrationTest;

import com.example.storeapp.config.OrderConfig;
import com.example.storeapp.model.OrderInfo;
import com.example.storeapp.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RabbitTemplate template;
    @Mock
    StoreService storeService;

    @Test
    public void orderPost() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setItemId(1);
        orderInfo.setOrderStatus("PLACED");
        orderInfo.setPaymentStatus("INITIATED");
        this.mockMvc.perform(post("/order").contentType("application/json").content(mapper.writeValueAsString(orderInfo)))
                .andExpect(status().isOk())
                .andExpect(content().string("Order placed successfully!!"));
        verify(template).convertAndSend(OrderConfig.EXCHANGE, OrderConfig.ROUTING_KEY, orderInfo);
        verify(template).convertAndSend(OrderConfig.EXCHANGE, OrderConfig.PAYMENT_ROUTING_KEY, orderInfo);
    }

    @Test
    public void getStatusOfOrderTest() throws Exception {
        OrderInfo order = new OrderInfo();
        order.setOrderId(1);
        order.setOrderStatus("PLACED");
        order.setQty(1);
        order.setName("Chicken Burger");
        Mockito.when(this.storeService.getStatus(1)).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/status/15")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"orderId\": 15,\n" +
                        "    \"orderNum\": \"dc3aabd8-bc76-4b56-97c9-8a5d68ba9a2c\",\n" +
                        "    \"name\": \"chicken Burger\",\n" +
                        "    \"itemId\": 1,\n" +
                        "    \"qty\": 6,\n" +
                        "    \"price\": 400.0,\n" +
                        "    \"orderStatus\": \"CANCELLED\",\n" +
                        "    \"paymentStatus\": \"ACCEPTED\",\n" +
                        "    \"accountNum\": \"12345\"\n" +
                        "}"));
    }
}
