package dao;

import entity.Customer;
import entity.Delivery;
import org.example.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDao implements Dao<Long, Customer>{

    private static final CustomerDao INSTANCE = new CustomerDao();
    private static String SAVE_SQL = """
            INSERT INTO customer (customer_id, person_name, phone, location)
            values (?,?,?,?)
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

    private Customer buildCustomer(ResultSet result) throws SQLException {
        return new Customer(result.getLong("customer_id"),
                result.getString("person_name"),
                result.getString("phone"),
                result.getString("location")
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
    public Optional<Customer> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Customer customer = null;
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
    public boolean update(Customer customer) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(3, customer.getPersonName());
            statement.setString(4,customer.getPhone());
            statement.setString(5,customer.getLocation());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL)) {
            List<Customer> customers = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                customers.add(buildCustomer(result));

            return customers;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public Customer save(Customer customer) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(3, customer.getPersonName());
            statement.setString(4,customer.getPhone());
            statement.setString(5,customer.getLocation());

            statement.executeUpdate();

            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                customer.setCustomerId(generatedKeys.getLong("id"));

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
