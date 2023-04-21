package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class CreateOrderDto {
    Integer customerId;
    Integer pizzaNameId;
    LocalDateTime dateGet;
}
