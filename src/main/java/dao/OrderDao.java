package dao;

import entity.Orders;
import entity.Pizza;
import org.example.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao implements Dao<Long, Orders>{

    private static final OrderDao INSTANCE = new OrderDao();
    private static String SAVE_SQL = """
            INSERT INTO orders (order_id, customer_id, pizza_name_id, date_get)
            values (?,?,?,?)
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
            date_get = ?
            WHERE order_id = ?;
            """;

    private static String FIND_BY_ID = FIND_ALL + """
            WHERE order_id = ?;
            """;

    private Orders buildOrder(ResultSet result) throws SQLException {
        return new Orders(result.getLong("order_id"),
                result.getLong("customer_id"),
                result.getLong("pizza_name-id"),
                result.getDate("date_get")
        );
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
            statement.setDate(4,orders.getDateGet());
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
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(2,orders.getCustomerId());
            statement.setLong(3,orders.getPizzaNameId());
            statement.setDate(3,orders.getDateGet());

            statement.executeUpdate();

            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                orders.setOrderid(generatedKeys.getLong("id"));

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
