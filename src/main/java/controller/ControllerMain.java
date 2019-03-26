package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Employee;
import service.LoggingService;
import java.io.IOException;


public class ControllerMain {
    private LoggingService loggingService = new LoggingService();
    private Employee currentEmployee;



    @FXML
    private TextArea logInTextArea;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    public void logInEmployee(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Employee employee = new Employee(username, password);

        boolean validUser = loggingService.loginUser(employee);
        if(validUser){
            setCurrentEmployee(employee);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/searchExcursion.fxml"));
            Parent tableViewParent = loader.load();
            Scene tableViewSceneInFile = new Scene(tableViewParent);
            //tableViewSceneInFile.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());
            //ControllerSearchExcursion controllerToSet = loader.getController();
            //controllerToSet.getController().setCurrentUser(getCurrentUser());

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewSceneInFile);
            window.show();
        }
        else {
            logInTextArea.setText("Username or password is incorrect!");
        }
    }
}
