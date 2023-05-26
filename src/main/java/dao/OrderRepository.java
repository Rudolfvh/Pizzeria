package dao;


import entity.Orders;

import javax.persistence.EntityManager;

public class OrderRepository extends RepositoryBase<Long, Orders> {

    public OrderRepository(EntityManager entityManager) {
        super(entityManager, Orders.class);
    }

}
