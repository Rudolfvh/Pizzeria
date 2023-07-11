package spring.database.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.database.entity.Pizza;

import java.util.List;
import java.util.Optional;


@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    @Override
    @Query("from Pizza p where p.pizzaId = :id")
    @NotNull
    Optional<Pizza> findById(@Param("id") @NotNull Integer id);

    @Override
    @Query("from Pizza")
    @NotNull
    List<Pizza> findAll();
}
