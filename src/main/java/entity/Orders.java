package entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "deliveries")
@EqualsAndHashCode(exclude = "deliveries")
@Table(name = "orders", schema = "public")
public class Orders {
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

    @OneToMany(mappedBy = "orders")
    private List<Delivery> deliveries;
    public void setCustomer(Customer customer){
        this.customer = customer;
        this.customer.getCustomer().add(this);
    }

    public void setPizza(Pizza pizza){
        this.pizza = pizza;
        this.pizza.getPizza().add(this);
    }
}
