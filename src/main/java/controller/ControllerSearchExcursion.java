package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Client;
import model.Employee;
import model.Excursion;
import service.BookingService;
import service.ClientService;
import service.ExcursionSerivce;
import validators.BookingValidator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerSearchExcursion implements Initializable {
    private ExcursionSerivce excursionSerivce = new ExcursionSerivce();
    private ClientService clientService = new ClientService();
    private BookingService bookingService = new BookingService();
    private BookingValidator bookingValidator = new BookingValidator();
    private Employee currentEmployee;

    @FXML
    private TextField landmarkField;
    @FXML
    private TextField startIntervalField;
    @FXML
    private TextField endIntervalField;

    @FXML
    private TableView<Excursion> excursionTableView;
    @FXML
    private TableColumn<Excursion, String> landmarkColumn;
    @FXML
    private TableColumn<Excursion, Integer> leavingHourColumn;
    @FXML
    private TableColumn<Excursion, Integer> availablePlacesColumn;
    @FXML
    private TableColumn<Excursion, String> transportCompanyColumn;
    @FXML
    private TableColumn<Excursion, Double> priceColumn;

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField telephoneNrField;
    @FXML
    private TextArea bookingTextArea;
    @FXML
    private TextField nrTicketsField;

    private Excursion getClickedRow(){return excursionTableView.getSelectionModel().getSelectedItem();
    }

    private void colorSoldOutExcursion()
    {
        excursionTableView.setRowFactory(row -> new TableRow<Excursion>(){
            @Override
            public void updateItem(Excursion item, boolean empty){
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else {
                    if (item.getAvailablePlaces() == 0){
                        for(int i=0; i<getChildren().size();i++){
                            //((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);
                            (getChildren().get(i)).setStyle("-fx-background-color: #ff0044");
                        }
                    } else
                    {

                        if (item.getAvailablePlaces() < 10)
                        {
                            for(int i=0; i<getChildren().size();i++)
                            {
                                //((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);
                                (getChildren().get(i)).setStyle("-fx-background-color: #f1ff40");
                            }
                        }

                    }
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        excursionTableView.refresh();
        List<Excursion> excursions = excursionSerivce.findAllExcursions();

        ObservableList<Excursion> observableList = FXCollections.observableArrayList(excursions);

        //excursionTableView.refresh();

        landmarkColumn.setCellValueFactory(new PropertyValueFactory<>("landmark"));
        leavingHourColumn.setCellValueFactory(new PropertyValueFactory<>("leavingHour"));
        availablePlacesColumn.setCellValueFactory(new PropertyValueFactory<>("availablePlaces"));
        transportCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("transportCompany"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //excursionTableView.refresh();
        excursionTableView.setItems(observableList);
        colorSoldOutExcursion();

        //excursionTableView.refresh();
    }

    private void loadTable(){
        excursionTableView.refresh();

        String landmark = landmarkField.getText();
        int startInterval = Integer.parseInt(startIntervalField.getText());
        int endInterval = Integer.parseInt(endIntervalField.getText());
        List<Excursion> excursions = excursionSerivce.findExcursions(landmark, startInterval, endInterval);

        ObservableList<Excursion> observableList = FXCollections.observableArrayList(excursions);

        //excursionTableView.refresh();

        landmarkColumn.setCellValueFactory(new PropertyValueFactory<>("landmark"));
        leavingHourColumn.setCellValueFactory(new PropertyValueFactory<>("leavingHour"));
        availablePlacesColumn.setCellValueFactory(new PropertyValueFactory<>("availablePlaces"));
        transportCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("transportCompany"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //excursionTableView.refresh();
        excursionTableView.setItems(observableList);
        colorSoldOutExcursion();

        //excursionTableView.refresh();
    }

    public void searchExcursions(ActionEvent event) {
        loadTable();
    }

    public void bookExcursion(ActionEvent event) {
        Excursion clickedExcursion = getClickedRow();
        int idExcursion = excursionSerivce.findExcursion(clickedExcursion.getLandmark(), clickedExcursion.getLeavingHour());

        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String telephoneNr = telephoneNrField.getText();

        Client client = new Client(firstName, lastName, telephoneNr);
        int idClient = clientService.findClient(client);
        int nrTickets = Integer.parseInt(nrTicketsField.getText());

        boolean checkTransaction = bookingValidator.validTransaction(clickedExcursion.getLandmark(), clickedExcursion.getLeavingHour(), nrTickets);

        if (checkTransaction){
            bookingService.addBooking(idClient, idExcursion, nrTickets);
            excursionSerivce.updateTickets(idExcursion, nrTickets);
            loadTable();
        }
        else {
            bookingTextArea.setText("Transaction couldn't be carried out. There are not enough available places.");
        }
    }

    public void logOut(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("/fxmls/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene tableViewSceneInFile = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewSceneInFile);
        window.show();
    }

}
