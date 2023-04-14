package dao;

import entity.Pizzeria;
import org.example.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzeriaDao implements Dao<String, Pizzeria>{

    private static final PizzeriaDao INSTANCE = new PizzeriaDao();
    private static String SAVE_SQL = """
            INSERT INTO pizzeria (code,country,city)
            values (?,?,?)
            """;

    private static String DELETE_SQL = """
            DELETE FROM pizzeria p
            WHERE p.code = ?
            """;

    private static String FIND_ALL = """
            SELECT p.code,
            p.country,
            p.city
            FROM pizzeria p
            """;

    private static String UPDATE_SQL = """
            UPDATE pizzeria SET
            country = ?,
            city = ?
            WHERE code = ?;
            """;

    private static String FIND_BY_ID = FIND_ALL + """
            WHERE code = ?;
            """;


    private Pizzeria buildPizzeria(ResultSet result) throws SQLException {
        return new Pizzeria(result.getString("code"),
                result.getString("country"),
                result.getString("city")
        );
    }

    @Override
    public boolean delete(String id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public Optional<Pizzeria> findById(String id) {
        try (var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(FIND_BY_ID)) {
            Pizzeria pizzeria = null;
            statement.setString(1, id);
            var result = statement.executeQuery();
            if (result.next())
                pizzeria = buildPizzeria(result);
            return Optional.ofNullable(pizzeria);
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public boolean update(Pizzeria pizzeria) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(2,pizzeria.getCountry());
            statement.setString(3,pizzeria.getCity());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public List<Pizzeria> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL)) {
            List<Pizzeria> pizzerias = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                pizzerias.add(buildPizzeria(result));

            return pizzerias;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    @Override
    public Pizzeria save(Pizzeria pizzeria) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(2,pizzeria.getCountry());
            statement.setString(3,pizzeria.getCity());

            statement.executeUpdate();

            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                pizzeria.setCode(generatedKeys.getString("code"));

            return pizzeria;
        } catch (SQLException e) {
            throw new DaoExeption(e);
        }
    }

    public static PizzeriaDao getInstance() {
        return INSTANCE;
    }

    private PizzeriaDao() {
    }
}
