package com.microservice.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.order_service.dto.OrderResponseDTO;
import com.microservice.order_service.dto.ProductDTO;
import com.microservice.order_service.entity.Order;
import com.microservice.order_service.repository.OrderRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // create a method to place order
    @PostMapping("/placeOrder")
    public Mono<ResponseEntity<OrderResponseDTO>> placeOrder(@RequestBody Order order){

        //fetch product details from product service
        //webclient -> intercommuncation with microservice, so need to give uri
        //retrive the data and map product dro to order response
        return webClientBuilder.build().get().uri("http://localhost:8081/product/" + order.getProductId()).retrieve().bodyToMono(ProductDTO.class).map(productDTO ->{
            OrderResponseDTO responseDTO = new OrderResponseDTO();
            
            responseDTO.setProductId(order.getProductId());
            responseDTO.setQuantiy(order.getQuantity());
            //set product details from product microservice which retreived adn stored in product dto
            responseDTO.setProductName(productDTO.getName());
            responseDTO.setProductPrice(productDTO.getPrice());
            responseDTO.setTotalprice(order.getQuantity() * productDTO.getPrice());

            //save
            orderRepository.save(order);
            responseDTO.setOrderId(order.getId());
            return ResponseEntity.ok(responseDTO);
        });
    }

    //get all order
    @GetMapping
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

}
