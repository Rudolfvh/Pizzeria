package spring.database.repository;



import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.database.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Override
    @Query("from Order o where  o.orderid = :id")
    @NotNull
    Optional<Order> findById(@Param("id") @NotNull Integer id);

    @Override
    @Query("from Order")
    @NotNull
    List<Order> findAll();

    @Query("from Order o where o.customer.userId = :id")
    @NotNull
    List<Order> findByCustomerId(@Param("id") @NotNull Integer id);
}
