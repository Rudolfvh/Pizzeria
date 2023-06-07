package spring.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CreatePizzaDto {
    Long pizzaId;
    String name;
    BigDecimal cost;
}
