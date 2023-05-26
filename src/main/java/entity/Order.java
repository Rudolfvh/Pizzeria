package entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders", schema = "public")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Customers")
public class Order implements Serializable, BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderid;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "pizza_name_id")
    private Pizza pizza;

    @Column(name = "date_get")
    private LocalDateTime dateGet;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
