package dao;

import entity.Delivery;
import org.example.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryDao implements Dao<Long, Delivery>{

    private static final DeliveryDao INSTANCE = new DeliveryDao();
    private static String SAVE_SQL = """
            INSERT INTO delivery_list (delivery_id, order_id, date_arrived, delivered_in_time, payment_method)
            values (?,?,?,?,?)
            """;

    private static String DELETE_SQL = """
            DELETE FROM delivery_list d
            WHERE d.delivery_id = ?
            """;

    private static String FIND_ALL = """
            SELECT d.delivery_id,
            d.order_id,
            d.date_arrived,
            d.delivered_in_time,
            d.payment_method
            FROM delivery_list d
            """;

    private static String UPDATE_SQL = """
            UPDATE delivery_list SET
            date_arrived = ?,
            delivered_in_time = ?,
            payment_method = ?
            WHERE delivery_id = ?;
            """;

    private static String FIND_BY_ID = FIND_ALL + """
            WHERE delivery_id = ?;
            """;

    private Delivery buildDelivery(ResultSet result) throws SQLException {
        return new Delivery(result.getLong("delivery_id"),
                result.getLong("order_id"),
                result.getTimestamp("date_arrived").toLocalDateTime(),
                result.getString("delivery_in_time"),
                result.getString("payment_method")
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
    public Optional<Delivery> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Delivery delivery = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                delivery = buildDelivery(result);
            return Optional.ofNullable(delivery);
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public boolean update(Delivery delivery) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setTimestamp(3, Timestamp.valueOf(delivery.getDateArrived()));
            statement.setString(4,delivery.getDeliveredInTime());
            statement.setString(5,delivery.getPaymentMethod());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public List<Delivery> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL)) {
            List<Delivery> deliveries = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                deliveries.add(buildDelivery(result));

            return deliveries;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public Delivery save(Delivery delivery) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(3, Timestamp.valueOf(delivery.getDateArrived()));
            statement.setString(4,delivery.getDeliveredInTime());
            statement.setString(5,delivery.getPaymentMethod());

            statement.executeUpdate();

            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                delivery.setDeliveryId(generatedKeys.getLong("id"));

            return delivery;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    public static DeliveryDao getInstance() {
        return INSTANCE;
    }

    private DeliveryDao() {
    }
}
