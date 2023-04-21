package dao;

import entity.Customer;
import entity.Role;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CustomerDao implements Dao<Integer, Customer> {

    private static final CustomerDao INSTANCE = new CustomerDao();

    private static final String SAVE_SQL =
            "INSERT INTO users (person_name,password, phone, role) VALUES (?, ?, ?, ?)";

    private static final String GET_BY_PHONE_AND_PASSWORD_SQL =
            "SELECT * FROM users WHERE password = ? AND phone = ?";



    @SneakyThrows
    public Integer find(String phone, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_PHONE_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, phone);

            var resultSet = preparedStatement.executeQuery();

            Customer customer = null;
            if (resultSet.next()) {
                customer = buildEntity(resultSet);
            }
            System.out.println(customer.getUserId());
            return customer.getUserId();
        }
    }

    @SneakyThrows
    public Optional<Customer> findByPhoneAndPassword(String phone, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_PHONE_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, phone);

            var resultSet = preparedStatement.executeQuery();

            Customer customer = null;
            if (resultSet.next()) {
                customer = buildEntity(resultSet);
            }
            return Optional.ofNullable(customer);
        }
    }


    private Customer buildEntity(ResultSet resultSet) throws java.sql.SQLException {
        return Customer.builder()
                .userId(resultSet.getObject("user_id", Integer.class))
                .personName(resultSet.getObject("person_name", String.class))
                .password(resultSet.getObject("password", String.class))
                .phone(resultSet.getObject("phone",String.class))
            //    .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .build();
    }

    @Override
    @SneakyThrows
    public Customer save(Customer entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getPersonName());
            preparedStatement.setObject(2, entity.getPassword());
            preparedStatement.setObject(3, entity.getPhone());
            preparedStatement.setObject(4, 1);
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setUserId(generatedKeys.getObject("user_id", Integer.class));

            return entity;
        }
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(Customer entity) {
        return false;
    }

    public static CustomerDao getInstance() {
        return INSTANCE;
    }
}
