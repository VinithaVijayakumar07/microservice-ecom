package com.microservice.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {


    private Long orderId;
    private Long productId;
    private int quantiy;
    private double totalprice;

    //product details

    private String productName;
    private double productPrice;



}
