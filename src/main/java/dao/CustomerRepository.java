package dao;

import entity.Customer;

import javax.persistence.EntityManager;

public class CustomerRepository extends RepositoryBase<Long, Customer> {
    public CustomerRepository(EntityManager entityManager) {
        super(entityManager, Customer.class);
    }

}