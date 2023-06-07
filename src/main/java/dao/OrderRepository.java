package dao;


import spring.entity.Order;

import javax.persistence.EntityManager;

public class OrderRepository extends RepositoryBase<Long, Order> {

    public OrderRepository(EntityManager entityManager) {
        super(entityManager, Order.class);
    }

}
