package dto;

import lombok.Builder;
import lombok.Value;

import java.sql.Date;
import java.time.LocalDateTime;

@Value
@Builder
public class OrderDto {
     Integer customerId;
     Integer pizzaNameId;
     LocalDateTime dateGet;
}
