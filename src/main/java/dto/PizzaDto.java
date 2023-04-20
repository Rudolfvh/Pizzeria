package dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PizzaDto {
     Integer pizzaId;
     String name;
     BigDecimal cost;
}
