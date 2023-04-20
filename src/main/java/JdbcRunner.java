import dao.CustomerDao;

import java.sql.*;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        CustomerDao customerDao = CustomerDao.getInstance();
        System.out.println(customerDao.findAll());
    }
}
