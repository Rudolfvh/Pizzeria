package dao;

import entity.Customer;
import entity.Orders;
import utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao implements Dao<Long, Orders>{

    private static final OrderDao INSTANCE = new OrderDao();
    private static String SAVE_SQL = """
            INSERT INTO orders (customer_id, pizza_name_id, date_get)
            values (?,?,?)
            """;

    private static String DELETE_SQL = """
            DELETE FROM orders o
            WHERE o.order_id = ?
            """;

    private static String FIND_ALL = """
            SELECT o.order_id,
            o.pizza_name_id,
            o.customer_id,
            o.date_get
            FROM orders o
            """;

    private static String UPDATE_SQL = """
            UPDATE orders SET
            customer_id = ?,
            pizza_name_id = ?,
            date_get = ?
            WHERE order_id = ?;
            """;

    private static String FIND_BY_ID = FIND_ALL + """
            WHERE order_id = ?;
            """;

    private Orders buildOrder(ResultSet result) throws SQLException {
        return Orders.builder()
                .pizzaNameId(result.getObject("pizza_name_id", Integer.class))
                .customerId(result.getObject("customer_id", Integer.class))
                .dateGet(result.getObject("date_get", LocalDateTime.class))
                .build();
    }
    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public Optional<Orders> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Orders order = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                order = buildOrder(result);
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public boolean update(Orders orders) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setLong(1,orders.getCustomerId());
            statement.setLong(2,orders.getPizzaNameId());
            statement.setTimestamp(3, Timestamp.valueOf(orders.getDateGet()));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public List<Orders> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL)) {
            List<Orders> orders = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                orders.add(buildOrder(result));

            return orders;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public Orders save(Orders orders) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1,orders.getCustomerId());
            preparedStatement.setObject(2,orders.getPizzaNameId());
            preparedStatement.setObject(3,orders.getDateGet());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next())
                orders.setOrderid(generatedKeys.getObject("pizza_name_id",Integer.class));

            return orders;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }

    private OrderDao() {
    }
}
