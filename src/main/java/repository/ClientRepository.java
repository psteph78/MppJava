package repository;

import model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.MyException;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepository implements IClientRepository{
    private JDBCUtils utils = new JDBCUtils();
    private static final Logger logger = LogManager.getLogger(BookingRepository.class);

    public int findOne(Client client){
        logger.traceEntry();
        try{
            logger.trace("Establishing connection with database.");
            Connection connection = utils.getConnection();
            logger.trace("Connection established.");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Select ID from Clienti where LastName = ? and FirstName = ? and TelephoneNr = ?");
            preparedStatement.setString(1,client.getLastName());
            preparedStatement.setString(2,client.getFirstName());
            preparedStatement.setString(3,client.getTelephoneNr());
            logger.trace("Preparing to execute query.");
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.trace("Query executed.");

            int id = -1;
            if (resultSet.next()){
                id = resultSet.getInt("ID");
            }

            if (id == -1 ){
                logger.trace("Preparing to add new client.");
                PreparedStatement preparedStatement1 = connection.prepareStatement("Insert into Clienti(LastName, FirstName, TelephoneNr)  values (?,?,?)");
                preparedStatement1.setString(1, client.getLastName());
                preparedStatement1.setString(2, client.getFirstName());
                preparedStatement1.setString(3, client.getTelephoneNr());
                logger.trace("Preparing to execute query.");
                int rs = preparedStatement1.executeUpdate();
                logger.trace("Query executed.");

                ResultSet resultSet1 = preparedStatement.executeQuery();
                if (resultSet.next()){
                    id = resultSet.getInt("ID");
                }
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
    public void add(Client client) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Client client) {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Client> findAll() {
        try {
            throw new MyException("Method not implemented.");
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
