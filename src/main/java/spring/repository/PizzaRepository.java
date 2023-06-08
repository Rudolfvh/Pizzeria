package spring.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.Pizza;

import java.util.List;
import java.util.Optional;


@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    String FIND_ALL_HQL = """
            select * FROM pizza p
            """;

    String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE p.pizza_id = :id
            """;

    @Override
    @Query(FIND_BY_ID_HQL)
    @NotNull
    Optional<Pizza> findById(@Param("id") @NotNull Integer id);

    @Override
    @Query(FIND_ALL_HQL)
    @NotNull
    List<Pizza> findAll();
}
