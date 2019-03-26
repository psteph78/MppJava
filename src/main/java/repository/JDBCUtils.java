package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private Properties properties = new Properties();
    private Connection connection = null;

    public Connection getConnection(){
        try{
            properties.load(new FileInputStream("C:\\Users\\Pricop Stefania\\IdeaProjects\\AgentieTurism\\src\\main\\resources\\bd.config"));
            properties.setProperty("PRAGMA foreign_keys", "ON");
        }
        catch(IOException e){
            e.printStackTrace();
        }

        String urlConnection = properties.getProperty("jdbc.url");
        String driverName = properties.getProperty("jdbc.driver");

        try {
            Class.forName(driverName);
            if(connection == null || connection.isClosed()){
                try{
                    connection = DriverManager.getConnection(urlConnection, properties);
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
