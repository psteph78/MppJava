package repository;

import model.Booking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.MyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingRepository implements IBookingRepository{
    private JDBCUtils utils = new JDBCUtils();
    private static final Logger logger = LogManager.getLogger(BookingRepository.class);

    public void addBooking(int idClient, int idExcursion, int nrTickets) {
        logger.traceEntry();
        try {
            logger.trace("Establishing connection with database.");
            Connection connection = utils.getConnection();
            logger.trace("Connection established.");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into Rezervari(IDClient, IDExcursie, NrTickets) values (?,?,?)");
            preparedStatement.setInt(1, idClient);
            preparedStatement.setInt(2, idExcursion);
            preparedStatement.setInt(3, nrTickets);
            logger.trace("Preparing to execute query.");
            int successful = preparedStatement.executeUpdate();
            logger.trace("Query executed.");
            if (successful == -1){
                throw new MyException("Record could not be inserted.");
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

    public void deleteBooking(int idClient, int idExcursion){
        logger.traceEntry();
        try{
            logger.trace("Establishing connection with database.");
            Connection connection = utils.getConnection();
            logger.trace("Connection established.");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Delete from Rezervari where IDClient = ? and IDExcursie = ?");
            preparedStatement.setInt(1, idClient);
            preparedStatement.setInt(2, idExcursion);
            logger.trace("Preparing to execute query.");
            int succesful = preparedStatement.executeUpdate();
            logger.trace("Query executed.");
            if (succesful == -1){
                throw new MyException("Record could not be deleted.");
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
    public void add(Booking booking) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Booking booking) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Booking booking) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Booking> findAll() {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }
}