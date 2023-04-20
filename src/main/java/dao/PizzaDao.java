package dao;

import entity.Customer;
import entity.Pizza;
import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.math.BigDecimal;
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

    private static String GET_BY_NAME_SQL = """
            SELECT *
            FROM pizza p
            WHERE p.name = ?;
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


    @SneakyThrows
    public Integer findByName(String name) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_NAME_SQL)) {
            preparedStatement.setString(1, name);

            var resultSet = preparedStatement.executeQuery();
            Pizza pizza = null;
            if (resultSet.next()) {
                pizza = buildPizza(resultSet);
            }
            return pizza.getPizzaId();
        }
    }

    private Pizza buildPizza(ResultSet result) throws SQLException {
        return new Pizza(result.getObject("pizza_id", Integer.class),
                result.getObject("name", String.class),
                result.getObject("cost", BigDecimal.class)
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
    @SneakyThrows
    public Pizza save(Pizza pizza) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1,pizza.getName());
            preparedStatement.setObject(2,pizza.getCost());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            pizza.setPizzaId(generatedKeys.getObject("pizza_id", Integer.class));

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
