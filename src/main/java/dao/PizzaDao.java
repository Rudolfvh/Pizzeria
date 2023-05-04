package dao;

import entity.Pizza;
import lombok.Cleanup;
import lombok.Getter;
import lombok.SneakyThrows;
import utils.SessionManager;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class PizzaDao implements Dao<Long, Pizza>{

    @Getter
    private static final PizzaDao INSTANCE = new PizzaDao();
    @Override
    public Optional<Pizza> findById(Long id) {
        @Cleanup var session = SessionManager.get();
        session.beginTransaction();
        Pizza pizza = session.get(Pizza.class, id);
        session.getTransaction().commit();
        return pizza != null
                ? Optional.of(pizza)
                : Optional.empty();
    }

    public Long findId(String name){
        return findByName(name).get().getPizzaId();
    }

    @SneakyThrows
    public Optional<Pizza> findByName(String name) {
        return findAll()
                .stream()
                .filter(pizza -> (pizza.getName().equals(name)))
                .findAny();
    }

    @Override
    public List<Pizza> findAll() {
        @Cleanup var session = SessionManager.get();
        session.beginTransaction();
        Query query = session.createQuery(" FROM " + Pizza.class.getSimpleName());
        session.getTransaction().commit();
        return (List<Pizza>) query.getResultList();
    }

}
