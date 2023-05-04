package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders", schema = "public")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderid;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "pizza_name_id")
    private Integer pizzaId;
    @Column(name = "date_get")
    private LocalDateTime dateGet;
}
