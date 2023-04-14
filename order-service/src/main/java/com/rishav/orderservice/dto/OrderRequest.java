package com.rishav.orderservice.dto;


import com.rishav.orderservice.model.OrderLineItems;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
