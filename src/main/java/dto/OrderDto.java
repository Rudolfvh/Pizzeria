package dto;

import lombok.Builder;
import lombok.Value;

import java.sql.Date;

@Value
@Builder
public class OrderDto {
     Integer customerId;
     Integer pizzaNameId;
     Date dateGet;
}
