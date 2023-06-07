package spring.dto;

import spring.entity.Customer;
import spring.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;


import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
public class OrderDto {
     Customer customer;
     Pizza pizza;
     LocalDateTime dateGet;
}
