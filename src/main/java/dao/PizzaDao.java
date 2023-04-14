package dao;

import entity.Pizza;
import entity.Pizzeria;
import org.example.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzaDao implements Dao<Long, Pizza>{

    private static final PizzaDao INSTANCE = new PizzaDao();
    private static String SAVE_SQL = """
            INSERT INTO pizza (name,cost)
            values (?,?)
            """;

    private static String DELETE_SQL = """
            DELETE FROM pizza p
            WHERE p.pizza_id = ?
            """;

    private static String FIND_ALL = """
            SELECT p.pizza_id,
            p.name,
            p.cost
            FROM pizza p
            """;

    private static String UPDATE_SQL = """
            UPDATE pizza SET
            name = ?,
            cost = ?
            WHERE pizza_id = ?;
            """;

    private static String FIND_BY_ID = FIND_ALL + """
            WHERE pizza_id = ?;
            """;

    private Pizza buildPizza(ResultSet result) throws SQLException {
        return new Pizza(result.getLong("pizza_id"),
                result.getString("name"),
                result.getBigDecimal("cost")
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
    public Optional<Pizza> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Pizza pizza = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                pizza = buildPizza(result);
            return Optional.ofNullable(pizza);
        } catch (SQLException e) {
             throw new DaoExeption(e);
        }
    }

    @Override
    public boolean update(Pizza pizza) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1,pizza.getName());
            statement.setBigDecimal(2,pizza.getCost());
            statement.setLong(3,pizza.getPizzaId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public List<Pizza> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL)) {
            List<Pizza> pizzas = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                pizzas.add(buildPizza(result));

            return pizzas;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public Pizza save(Pizza pizza) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,pizza.getName());
            statement.setBigDecimal(2,pizza.getCost());

            statement.executeUpdate();

            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                pizza.setPizzaId(generatedKeys.getLong("id"));

            return pizza;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    public static PizzaDao getInstance() {
        return INSTANCE;
    }

    private PizzaDao() {
    }
}
