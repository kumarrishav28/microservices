package com.rishav.orderservice.service;


import com.rishav.orderservice.dto.InventoryResponse;
import com.rishav.orderservice.dto.OrderLineItemsDto;
import com.rishav.orderservice.dto.OrderRequest;
import com.rishav.orderservice.model.Order;
import com.rishav.orderservice.model.OrderLineItems;
import com.rishav.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional

public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {

        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponse [] inventoryResponses = webClientBuilder.build().get()
                .uri("http://localhost:8082/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve().bodyToMono(InventoryResponse[].class).block();

        boolean allProductIsInStock= Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);


        if (allProductIsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not available in stock ,please try again later");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;

    }
}
