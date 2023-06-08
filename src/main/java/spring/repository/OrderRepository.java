package spring.repository;



import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    String FIND_ALL_HQL = """
            FROM orders o
            """;

    String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE o.order_id = :id
            """;

    String FIND_BY_CUSTOMER_ID_HQL = FIND_ALL_HQL + """
             WHERE o.customer_id = :customerId
            """;

    @Override
    @Query(FIND_BY_ID_HQL)
    @NotNull
    Optional<Order> findById(@Param("id") @NotNull Integer id);

    @Override
    @Query(FIND_ALL_HQL)
    @NotNull
    List<Order> findAll();

}
