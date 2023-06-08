package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.Customer;

import java.util.Optional;



public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    String FIND_ALL_HQL = """
         FROM Users u
            """;

    String FIND_BY_PHONE_AND_PASS_HQL = FIND_ALL_HQL + """
             WHERE u.phone = :phone AND u.password = :password
            """;
    @Query(FIND_BY_PHONE_AND_PASS_HQL)
    Optional<Customer> findByEmailAndPassword (@Param("phone") String email, @Param("password") String password);
}