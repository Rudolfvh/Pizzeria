package spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.database.entity.Customer;

import java.util.Optional;



public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByPhoneAndPassword (@Param("phone") String phone, @Param("password") String password);
}