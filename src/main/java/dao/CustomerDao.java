package dao;

import entity.User;
import org.example.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDao implements Dao<Long, User>{

    private static final CustomerDao INSTANCE = new CustomerDao();
    private static String SAVE_SQL = """
            INSERT INTO customer (person_name, phone, location)
            values (?,?,?)
            """;

    private static String DELETE_SQL = """
            DELETE FROM customer c
            WHERE c.customer_id = ?
            """;

    private static String FIND_ALL = """
            SELECT c.customer_id,
            c.person_name,
            c.phone,
            c.location
            FROM customer c
            """;

    private static String UPDATE_SQL = """
            UPDATE customer SET
            person_name = ?,
            phone = ?,
            location = ?
            WHERE customer_id = ?;
            """;

    private static String FIND_BY_ID = FIND_ALL + """
            WHERE customer_id = ?;
            """;

    private User buildCustomer(ResultSet result) throws SQLException {
        return new User(result.getLong("customer_id"),
                result.getString("person_name"),
                result.getString("password"),
                result.getString("phone"),
                result.getString("location"),
                result.getString("role")
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
    public Optional<User> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            User customer = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                customer = buildCustomer(result);
            return Optional.ofNullable(customer);
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public boolean update(User customer) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, customer.getPersonName());
            statement.setString(2,customer.getPhone());
            statement.setString(3,customer.getLocation());
            statement.setLong(4,customer.getUserId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL)) {
            List<User> customers = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                customers.add(buildCustomer(result));

            return customers;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public User save(User customer) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(3, customer.getPersonName());
            statement.setString(4,customer.getPhone());
            statement.setString(5,customer.getLocation());

            statement.executeUpdate();

            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                customer.setUserId(generatedKeys.getLong("id"));

            return customer;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }
    public static CustomerDao getInstance() {
        return INSTANCE;
    }

    private CustomerDao() {
    }
}
