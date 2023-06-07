package spring.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "pizza", schema = "public")
@EqualsAndHashCode(exclude = "orders")
@ToString(exclude = "orders")
public class Pizza implements Serializable, BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pizzaId;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;

    @OneToMany(mappedBy = "pizza")
    private Set<Order> orders = new HashSet<>();

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
