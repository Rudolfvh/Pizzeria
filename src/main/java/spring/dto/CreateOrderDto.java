package spring.dto;

import spring.entity.Customer;
import spring.entity.Pizza;
import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Value
@Builder
public class CreateOrderDto {
    @Valid
    Customer customer;
    Pizza pizza;
    LocalDateTime dateGet;
}
