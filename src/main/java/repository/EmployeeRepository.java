package repository;

import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.MyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository implements IEmployeeRepository{
    private JDBCUtils utils = new JDBCUtils();
    private static final Logger logger = LogManager.getLogger(BookingRepository.class);


    @Override
    public boolean loginUser(Employee employee){
        logger.traceEntry();
        try{
            logger.trace("Establishing connection with database.");
            Connection connection = utils.getConnection();
            logger.trace("Connection established.");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from Angajati where Username=?");
            preparedStatement.setString(1, employee.getUsername());
            logger.trace("Preparing to execute query.");
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.trace("Query executed.");

            boolean isEmpty = true;
            if(resultSet.next()){
                isEmpty = false;
                String passwd = resultSet.getString("Password");
                if (!passwd.equals(employee.getPassword())){
                    throw new MyException("Incorrect password");
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
            logger.trace("Connection closed.");

            if(isEmpty){
                throw new MyException("Invalid username");
            }
        } catch (SQLException e) {
            logger.warn("Exception thrown: " + e.toString());
            e.printStackTrace();
            return false;
        } catch (MyException e) {
            logger.warn("Exception thrown: " + e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void add(Employee employee) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employee employee) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Employee> findAll() {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
