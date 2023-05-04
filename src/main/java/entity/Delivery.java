package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "delivery", schema = "public")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;
    @Convert(converter = Orders.class)
    @Column(name = "order_id")
    private Orders orders;
    @Column(name = "date_arrived")
    private LocalDateTime dateArrived;
    @Column(name = "delivered_in_time")
    private String deliveredInTime;
    @Column(name = "payment_method")
    private String paymentMethod;
}
