package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.entity.Customer;


@Repository
public class CustomerRepository extends JpaRepository<Customer,Integer> {
    public CustomerRepository(EntityManager entityManager) {
        super(entityManager, Customer.class);
    }

}