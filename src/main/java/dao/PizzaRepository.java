package dao;

import spring.entity.Pizza;

import javax.persistence.EntityManager;

public class PizzaRepository extends RepositoryBase<Long, Pizza>{
    public PizzaRepository(EntityManager entityManager) {
        super(entityManager, Pizza.class);
    }

}
