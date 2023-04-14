package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    private Long deliveryId;
    private Long orderId;
    private LocalDateTime dateArrived;
    private String deliveredInTime;
    private String paymentMethod;
}
