package dto;

import entity.Customer;
import entity.Pizza;
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
