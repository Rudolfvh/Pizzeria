package dao;

import entity.User;
import entity.Role;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.example.ConnectionManager;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL =
            "INSERT INTO customer (person_name,password, phone,location) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_BY_PASSWORD_SQL =
            "SELECT * FROM users WHERE password = ?";


    @SneakyThrows
    public Optional<User> findByPassword(String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_PASSWORD_SQL)) {
            preparedStatement.setString(1, password);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }


    private User buildEntity(ResultSet resultSet) throws java.sql.SQLException {
        return User.builder()
                .userId(resultSet.getObject("id", Integer.class))
                .personName(resultSet.getObject("name", String.class))
                .password(resultSet.getObject("password", String.class))
                .location(resultSet.getObject("location", String.class))
                .phone(resultSet.getObject("phone",String.class))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .build();
    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getPersonName());
            preparedStatement.setObject(2, entity.getPassword());
            preparedStatement.setObject(3, entity.getLocation());
            preparedStatement.setObject(4, entity.getPhone());
            preparedStatement.setObject(5, entity.getRole().name());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setUserId(generatedKeys.getObject("id", Integer.class));

            return entity;
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
