package dto;

import lombok.Builder;
import lombok.Value;

import java.sql.Date;

@Value
@Builder
public class CreateOrderDto {
    Integer customerId;
    Integer pizzaNameId;
    Date dateGet;
}
