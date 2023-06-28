package spring.database.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.database.entity.Delivery;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    String FIND_ALL_HQL = """
            select * FROM delivery_list d
            """;

    String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE d.delivery_id = :id
            """;

    @Override
    @Query(FIND_BY_ID_HQL)
    @NotNull
    Optional<Delivery> findById(@Param("id") @NotNull Integer id);

    @Override
    @Query(FIND_ALL_HQL)
    @NotNull
    List<Delivery> findAll();
}
