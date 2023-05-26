package utils;

import entity.Customer;
import entity.Order;
import entity.Pizza;
import entity.Role;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UtilityClass
public class TestDataImporter {

    public void importData(SessionFactory sessionFactory){
        @Cleanup Session session = sessionFactory.openSession();

        Customer matvey = saveCustomer(session,"Matvey","96321",
                "+375333827732",Role.ADMIN);
        Customer maks = saveCustomer(session,"Maks","31251",
                "+375329125332",Role.USER);
        Customer ihor = saveCustomer(session,"Ihor","97413",
                "+375334237421",Role.USER);
        Customer georgy = saveCustomer(session,"Georgy","21464",
                "+375332421229",Role.USER);

        Pizza barbecueChicken = savePizza(session,"Цыплёнок барбекю",
                BigDecimal.valueOf(195));
        Pizza ranchPizza = savePizza(session,"Ранч пицца",
                BigDecimal.valueOf(123));
        Pizza spicyChili = savePizza(session,"Острая чили",
                BigDecimal.valueOf(156));
        Pizza pepperoni = savePizza(session,"Пепперони",
                BigDecimal.valueOf(200));
        Pizza chickenDorblu = savePizza(session,"Цыплёнок дорблю",
                BigDecimal.valueOf(250));
        Pizza hawaiian = savePizza(session,"Гавайская",
                BigDecimal.valueOf(360));

        saveOrders(session,matvey,barbecueChicken,LocalDateTime.now());
        saveOrders(session,maks,spicyChili,LocalDateTime.now());
        saveOrders(session,ihor,pepperoni,LocalDateTime.now());
        saveOrders(session,matvey,hawaiian,LocalDateTime.now());
        saveOrders(session,ihor,barbecueChicken,LocalDateTime.now());
        saveOrders(session,georgy,chickenDorblu,LocalDateTime.now());
    }

    private Customer saveCustomer(Session session,
                                  String name,
                                  String password,
                                  String phone,
                                  Role role){

        Customer customer = Customer.builder().personName(name)
                .password(password)
                .phone(phone)
                .role(role).build();
        session.save(customer);
        return customer;
    }

    private Pizza savePizza(Session session,
                            String name,
                            BigDecimal cost){
        Pizza pizza = Pizza.builder().name(name)
                .cost(cost).build();
        session.save(pizza);
        return pizza;
    }

    private Order saveOrders(Session session,
                             Customer customer,
                             Pizza pizza,
                             LocalDateTime date){
        Order order = Order.builder().customer(customer)
                .pizza(pizza)
                .dateGet(date).build();
        session.save(order);
        return order;
    }
}
