package repository;

import model.Excursion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.MyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcursionRepository implements IExcursionRepository{
    private JDBCUtils utils = new JDBCUtils();
    private static final Logger logger = LogManager.getLogger(BookingRepository.class);

    public List<Excursion> findExcursions(String landmark, int beginTimeInterval, int endTimeInterval){
        logger.traceEntry();
        try{
            logger.trace("Establishing connection with database.");
            Connection connection = utils.getConnection();
            logger.trace("Connection established.");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from Excursii where (Landmark = ?) and (LeavingHour between ? and ?)");
            preparedStatement.setString(1,landmark);
            preparedStatement.setInt(2,beginTimeInterval);
            preparedStatement.setInt(3,endTimeInterval);
            logger.trace("Preparing to execute query.");
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.trace("Query executed.");

            boolean isEmpty = true;
            List<Excursion> excursions = new ArrayList<>();
            while(resultSet.next()){
                isEmpty = false;
                String landMark = resultSet.getString("Landmark");
                int leavingHour = resultSet.getInt("LeavingHour");
                int availablePlaces = resultSet.getInt("AvailablePlaces");
                String transportCompany = resultSet.getString("TransportCompany");
                double price = resultSet.getDouble("Price");
                Excursion excursion = new Excursion(landMark, leavingHour, availablePlaces, transportCompany, price);
                excursions.add(excursion);
            }

            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
            logger.trace("Connection closed.");

            if (isEmpty){
                return null;
            }

            return excursions;
        } catch (SQLException e) {
            logger.warn("Exception thrown: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Excursion> findAllExcursions() {
        logger.traceEntry();
        try{
            logger.trace("Establishing connection with database.");
            Connection connection = utils.getConnection();
            logger.trace("Connection established.");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from Excursii");
            logger.trace("Preparing to execute query.");
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.trace("Query executed.");

            boolean isEmpty = true;
            List<Excursion> excursions = new ArrayList<>();
            while(resultSet.next()){
                isEmpty = false;
                String landmark = resultSet.getString("Landmark");
                int leavingHour = resultSet.getInt("LeavingHour");
                int availablePlaces = resultSet.getInt("AvailablePlaces");
                String transportCompany = resultSet.getString("TransportCompany");
                double price = resultSet.getDouble("Price");
                Excursion excursion = new Excursion(landmark, leavingHour, availablePlaces, transportCompany, price);
                excursions.add(excursion);
            }

            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
            logger.trace("Connection closed.");

            if (isEmpty){
                return null;
            }

            return excursions;
        } catch (SQLException e) {
            logger.warn("Exception thrown: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public int findAvailablePlaces(String landmark, int leavingHour){
        logger.traceEntry();
        try{
            logger.trace("Establishing connection with database.");
            Connection connection = utils.getConnection();
            logger.trace("Connection established.");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Select AvailablePlaces from Excursii where (Landmark = ?) and (LeavingHour = ?)");
            preparedStatement.setString(1,landmark);
            preparedStatement.setInt(2,leavingHour);
            logger.trace("Preparing to execute query.");
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.trace("Query executed.");

            int availablePlaces = -1;
            if (resultSet.next()){
                availablePlaces = resultSet.getInt("AvailablePlaces");
            }

            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
            logger.trace("Connection closed.");

            return availablePlaces;
        } catch (SQLException e) {
            logger.warn("Exception thrown: " + e.toString());
            e.printStackTrace();
            return -1;
        }
    }

    public void updateTickets(int idExcursion, int nrTickets){
        logger.traceEntry();
        try{
            logger.trace("Establishing connection with database.");
            Connection connection = utils.getConnection();
            logger.trace("Connection established.");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatementFind = connection.prepareStatement("Select AvailablePlaces from Excursii where ID = ?");
            preparedStatementFind.setInt(1,idExcursion);
            logger.trace("Preparing to execute query.");
            ResultSet resultSet = preparedStatementFind.executeQuery();
            logger.trace("Query executed.");

            int availablePlaces = 0;
            if (resultSet.next()){
                availablePlaces = resultSet.getInt("AvailablePlaces");
            }
            logger.trace("Preparing to execute query.");
            PreparedStatement preparedStatement = connection.prepareStatement("Update Excursii set AvailablePlaces = ? where ID = ?");
            int updatedPlaces = availablePlaces - nrTickets;
            preparedStatement.setInt(1,updatedPlaces);
            preparedStatement.setInt(2,idExcursion);
            logger.trace("Query executed.");

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0){
                throw new MyException("Update could not be completed");
            }
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
            logger.trace("Connection closed.");

        } catch (SQLException e) {
            logger.warn("Exception thrown: " + e.toString());
            e.printStackTrace();
        } catch (MyException e) {
            logger.warn("Exception thrown: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public int findOne(String landmark, int leavingHour) {
        logger.traceEntry();
        try{
            logger.trace("Establishing connection with database.");
            Connection connection = utils.getConnection();
            logger.trace("Connection established.");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatementFind = connection.prepareStatement("Select ID from Excursii where Landmark = ? and LeavingHour = ?");
            preparedStatementFind.setString(1, landmark);
            preparedStatementFind.setInt(2, leavingHour);
            logger.trace("Preparing to execute query.");
            ResultSet resultSet = preparedStatementFind.executeQuery();
            logger.trace("Query executed.");

            int id = -1;

            if (resultSet.next()){
                id = resultSet.getInt("ID");
            }

            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
            logger.trace("Connection closed.");
            return id;

        } catch (SQLException e) {
            logger.warn("Exception thrown: " + e.toString());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void add(Excursion excursion) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Excursion excursion) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Excursion excursion) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Excursion> findAll() {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
