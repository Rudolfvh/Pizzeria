package spring.dto;

import spring.database.entity.Customer;
import spring.database.entity.Pizza;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class CreateOrderDto {

    Customer customer;
    Pizza pizza;
    LocalDateTime dateGet;
}
