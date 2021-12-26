package com.example.osago;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;

public class RegistrationCarController implements Initializable {

    @FXML
    private TextField VIN;
    @FXML
    private TextField carBrand;
    @FXML
    private TextField carModel;

    @FXML
    private TextField carPower;

    @FXML
    private ChoiceBox<String> carRegion;
    @FXML
    private Label checkL;

    @FXML
    private CheckBox Drivers;

    @FXML
    private TextField numberField;

    @FXML
    private TextField yearOfCar;

    @FXML
    private ChoiceBox<String> periodField;

    //Выбор региона с помочью ChoiceBox, подключенного к таблице коэффициентов
    private String[] arrayRegions = new String[161];
    private String[] periods = {"3 месяца", "4 месяца", "5 месяцев", "6 месяцев", "7 месяцев", "8 месяцев", "9 месяцев", "1 год"};

    DatabaseHandler databaseHandler = new DatabaseHandler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //подключение к таблице коэффициентов для регионов
        Statement statement = null;
        try {
            statement = databaseHandler.getDbConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String queryRatio = "SELECT * FROM region_ratio";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(queryRatio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                arrayRegions[i] = resultSet.getString(2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            i++;
        }
        carRegion.getItems().addAll(arrayRegions);
        periodField.getItems().addAll(periods);
    }

    boolean check; //переменная для проверки ограничения водителей

    public void signUpNewCar() throws SQLException, ClassNotFoundException {
        //проверка на пустоту полей, если пустые - нельзя сохранить и предупреждение
        if (yearOfCar.getText().trim().isEmpty() || carBrand.getText().trim().isEmpty() || carModel.getText().trim().isEmpty() ||
                carPower.getText().trim().isEmpty() || VIN.getText().trim().isEmpty() || numberField.getText().trim().isEmpty()) {
            checkL.setText("Проверьте, что поля не пустые!");
        } else {
            //регулярные выражения для проверки правильности введенных данных
            boolean resultYear = yearOfCar.getText().matches("^([1-2]{1}[0-9]{0,3})$");
            boolean resultBrand = carBrand.getText().matches("^([А-Я]{1}|[A-Z]{1})|([А-Яа-я]+|[A-Za-z]+)|(\\-)?(([а-яА-Я]+)|([a-zA-Z]+))$");
            boolean resultModel = carModel.getText().matches("^([А-Я]{1}|[A-Z]{1})|([А-Яа-я]+|[A-Za-z]+)|(\\-)?(([а-яА-Я]+)|([a-zA-Z]+))$");
            boolean resultPower = carPower.getText().matches("^([1-9]{1}[0-9]{1,3})$");
            boolean resultVIN = VIN.getText().matches("^([A-Z0-9]){17}$");
            boolean resultNumber = numberField.getText().matches("^([А-Я]{1}[0-9]{3}[А-Я]{2})$");

            //  если все данные введены верно, то сохраняем в таблицу, иначе предупреждение о необходимости проверки данных
            if (resultYear && resultBrand && resultModel && resultPower && resultVIN && resultNumber) {
                //запись данных из формы в переменные
                String year = yearOfCar.getText();
                String brand = carBrand.getText();
                String model = carModel.getText();
                String power = carPower.getText();
                String region = (String) carRegion.getValue();
                String vin = VIN.getText();
                String period = (String) periodField.getValue();
                String number = numberField.getText();
                String drivers = "";

                if (Drivers.isSelected()) {
                    drivers = "Ограничено";
                    check = true;
                } else {
                    drivers = "Не ограничено";
                    check = false;
                }
                //создаем объект класса авто и передаем туда полученные данные
                Car car = new Car(year, brand, model, power, region, drivers, vin, period, number);
                databaseHandler.signUpCar(car); //вызываем метод для записи переданного объекта в таблицу
                checkL.setText("Сохранено!");
            } else {
                checkL.setText("Проверьте правильность введенных данных!");
            }
        }
    }


    private Stage stage;
    private Scene scene;

    public void switchToSceneAboutDriver(ActionEvent event) throws IOException {
        Parent root;
        if (check == true) { //если количество водителей ограничено, то на форму добавления водителей
            root = FXMLLoader.load(getClass().getResource("/com/example/osago/driverInfo.fxml"));
        } else { //иначе сразу к страхователю
            root = FXMLLoader.load(getClass().getResource("/com/example/osago/insurerInfo.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneHomepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/osago/homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
