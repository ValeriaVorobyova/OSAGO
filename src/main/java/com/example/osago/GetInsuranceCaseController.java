package com.example.osago;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class GetInsuranceCaseController {
    @FXML
    private Label carBrandField;
    @FXML
    private Label carModelField;
    @FXML
    private Label carNumberField;
    @FXML
    private Label carYearField;
    @FXML
    private Label firstNameDriverField;
    @FXML
    private Label firstNameInsurerField;
    @FXML
    private Label lastNameDriverField;
    @FXML
    private Label DriverPassportField;
    @FXML
    private Label insurerPassportField;
    @FXML
    private DatePicker dateField;
    @FXML
    private Label checkField;
    @FXML
    private Label lastNameInsurerField;
    @FXML
    private Label patronymicDriverField;
    @FXML
    private Label patronymicInsurerField;
    @FXML
    private Label infoField;
    @FXML
    private TextField searchByCarField;
    @FXML
    private Label descriptionField;
    @FXML
    private Label paymentField;

    DatabaseHandler databaseHandler = new DatabaseHandler();
    public void search () throws SQLException, ClassNotFoundException {
        //подключение к таблице страховых случаев
        String query = "SELECT * FROM " + ConstInsuranceCase.INSURANCE_CASE_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        LocalDate dateForm = dateField.getValue();
        if (!searchByCarField.getText().trim().isEmpty()) { //проверка, что поле не пустое
            boolean resultNumber = searchByCarField.getText().matches("^([А-Я]{1}([-]{1})[0-9]{3}([-]{1})[А-Я]{2})$");
            if(resultNumber) { //если правильно введен номер авто
                String carNumber = "", date = "", description = "";
                double payment = 0;
                while (resultSet.next()) {
                    carNumber = resultSet.getString(8);
                    date =  resultSet.getString(7);
                    description = resultSet.getString(9);
                    payment = resultSet.getDouble(6);
                    if (searchByCarField.getText().equals(carNumber) && ((dateForm.toString()).equals(date))) {
                        int idCar = getIDCar();
                        getInsurer(idCar);
                        getCarInfo(idCar);
                        getDriver(idCar);
                        infoField.setText("найдена");
                        descriptionField.setText(description);
                        paymentField.setText(payment + " руб.");
                        break;
                    }
                    else {
                        infoField.setText("не найдена");
                        descriptionField.setText("");
                        lastNameInsurerField.setText("");
                        firstNameInsurerField.setText("");
                        patronymicInsurerField.setText("");
                        insurerPassportField.setText("");
                        lastNameDriverField.setText("");
                        firstNameDriverField.setText("");
                        patronymicDriverField.setText("");
                        DriverPassportField.setText("");
                        carBrandField.setText("");
                        carModelField.setText("");
                        carYearField.setText("");
                        carNumberField.setText("");
                        paymentField.setText("");
                    }
            }
        }
            else {
                checkField.setText("Проверьте номер авто!");
            }
       }
        else {
            checkField.setText("Пустое поле!");
        }
    }

    public void getInsurer(int id) throws SQLException, ClassNotFoundException {
        int idcar = id;
        String query = "SELECT * FROM " + ConstInsurer.INSURERS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String lastName = "", firstName = "", patronymic = "", passport = "";
        int idCarfromD;
        while (resultSet.next()) {
            idCarfromD = resultSet.getInt(2);
            if (idcar == idCarfromD) {
                lastName = resultSet.getString(4);
                firstName = resultSet.getString(3);
                patronymic = resultSet.getString(5);
                passport =  resultSet.getString(7);
            }
        }
        lastNameInsurerField.setText(lastName);
        firstNameInsurerField.setText(firstName);
        patronymicInsurerField.setText(patronymic);
        insurerPassportField.setText(passport);
    }

    public void getDriver(int id) throws SQLException, ClassNotFoundException {
        String idcar = Integer.toString(id);
        String query = "SELECT * FROM " + ConstDrivers.DRIVERS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String lastName = "", firstName = "", patronymic = "", passport = "";
        String idCarfromD;
        while (resultSet.next()) {
            idCarfromD = resultSet.getString(2);
            if (idcar.equals(idCarfromD)) {
                lastName = resultSet.getString(4);
                firstName = resultSet.getString(3);
                patronymic = resultSet.getString(5);
                passport =  resultSet.getString(7);
            }
        }
        lastNameDriverField.setText(lastName);
        firstNameDriverField.setText(firstName);
        patronymicDriverField.setText(patronymic);
        DriverPassportField.setText(passport);
    }

    //метод получения данных об авто для вывода в поля на форме
    public void getCarInfo(int idCar) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String brand = "", model = "", year = "", number = "";
        int carID = idCar; //получение id введенного авто
        int id = -1;
        while (resultSet.next()) {
            id = resultSet.getInt(1);
            if (carID == id) { // //если id авто совпадает с id авто, номер которого ввели, в коллекцию добавляется новый объект с данными из колон
                year = resultSet.getString(2);
                brand = resultSet.getString(3);
                model = resultSet.getString(4);
                number = resultSet.getString(10);
            }
        }
        carBrandField.setText(brand);
        carModelField.setText(model);
        carYearField.setText(year);
        carNumberField.setText(number);
    }



    public int getIDCar() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String carNumber = "";
        int id = -1;
        while (resultSet.next()) {
            carNumber = resultSet.getString(10);
            if (carNumber.equals(searchByCarField.getText())) {
                id = resultSet.getInt(1);
            }
        }
        System.out.println(id);
        return id;
    }

    //перемещение на главную форму
    private Stage stage;
    private Scene scene;
    public void switchToSceneHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/osago/homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
