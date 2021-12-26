package com.example.osago;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteController {

    @FXML
    private Label VINField;

    @FXML
    private Label brandField;

    @FXML
    private TextField carNumberField;

    @FXML
    private Label firstNameField;

    @FXML
    private Label lastNameField;

    @FXML
    private Label modelField;

    @FXML
    private Label passportDateField;

    @FXML
    private Label passportField;

    @FXML
    private Label patronymicField;

    @FXML
    private Label yearField;

    DatabaseHandler databaseHandler = new DatabaseHandler();

    //метод поиска данных по введенному номеру авто
    public void Find() throws SQLException, ClassNotFoundException {
        //для поиска данных подключаемся к таблице автомобилей
        String query = "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int carID = -1;
        String brand = "", model = "", year = "", number = "", vin = "";
        while (resultSet.next()) {
            number = resultSet.getString(10);
            if (carNumberField.getText().equals(number)) { //если номер машины совпадает с номером из таблицы, записываем данные в переменные
                year = resultSet.getString(2);
                brand = resultSet.getString(3);
                model = resultSet.getString(4);
                vin = resultSet.getString(8);
                carID = resultSet.getInt(1); //получаем id авто для поиска страхователя
            }
        }
        //устанавливаем полям полученные значения
        brandField.setText(brand);
        modelField.setText(model);
        yearField.setText(year);
        VINField.setText(vin);

        //для поиска данных подключаемся к таблице страхователей
        String query2 = "SELECT * FROM " + ConstInsurer.INSURERS_TABLE;
        Statement statement2 = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet2 = statement2.executeQuery(query2);
        String lastName = "", firstName = "", patronymic = "", passport = "", passportDate = "";
        int idcar = -1;
        while (resultSet2.next()) {
            idcar = resultSet2.getInt(2);
            if (idcar == carID) { //если id авто из таблицы страхователей совпадает с id из таблицы авто, записываем данные в переменные
                lastName = resultSet2.getString(4);
                firstName = resultSet2.getString(3);
                patronymic = resultSet2.getString(5);
                passport = resultSet2.getString(7);
                passportDate = resultSet2.getString(8);
            }
        }
        //устанавливаем полям полученные значения
        lastNameField.setText(lastName);
        firstNameField.setText(firstName);
        patronymicField.setText(patronymic);
        passportField.setText(passport);
        passportDateField.setText(passportDate);
    }


    //метод удаления данных из таблиц, где id авто равен id авто, номер которого ввели
    public void Deleting() throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String number = "";
        String id = "";
        while (resultSet.next()) {
            number = resultSet.getString(10);
            if (carNumberField.getText().equals(number)) { //получаем id авто для поиска данных в других связанных таблицах
             id = resultSet.getString(1);
            }
        }

        //запрос на удаление данных из таблицы водителей, где id авто равен id авто, номер которого ввели
        String queryDeleteFromDrivers = "DELETE FROM driverdata WHERE idcar = " + id;
        Statement statementForDDelete = databaseHandler.getDbConnection().createStatement();
        int resultSetDeleteD = statementForDDelete.executeUpdate(queryDeleteFromDrivers);

        //запрос на удаление данных из таблицы страхователей, где id авто равен id авто, номер которого ввели
        String queryDeleteFromInsurers = "DELETE FROM insurerdata WHERE idcar = " + id;
        Statement statementForIDelete = databaseHandler.getDbConnection().createStatement();
        int resultSetDeleteI = statementForIDelete.executeUpdate(queryDeleteFromInsurers);

        //запрос на удаление данных из таблицы авто, где id авто равен id авто, номер которого ввели
        String queryDeleteFromCars = "DELETE FROM carsdata WHERE idcars = " + id;
        Statement statementForCDelete = databaseHandler.getDbConnection().createStatement();
        int resultSetDeleteC = statementForCDelete.executeUpdate(queryDeleteFromCars);

        //запрос на удаление данных из таблицы страховых полюсов, где id авто равен id авто, номер которого ввели
        String queryDeleteFromPolicy = "DELETE FROM insurance_policy WHERE carID = " + id;
        Statement statementForPDelete = databaseHandler.getDbConnection().createStatement();
        int resultSetDeleteP = statementForPDelete.executeUpdate(queryDeleteFromPolicy);
    }

    private Stage stage;
    private Scene scene;

    public void switchToSceneHomepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/osago/homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
