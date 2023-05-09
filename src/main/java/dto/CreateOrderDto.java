package dto;

import entity.Customer;
import entity.Pizza;
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
