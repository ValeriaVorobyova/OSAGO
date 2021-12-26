package com.example.osago;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class RegistrationDriverController implements Initializable {


    @FXML
    private DatePicker birthDateField;

    @FXML
    private DatePicker dateOfPassportField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField identifyCardField;

    @FXML
    private TextField lastNameField;


    @FXML
    private TextField passportField;

    @FXML
    private TextField patronymicField;


    @FXML
    private Label checkL;

    @FXML
    private ChoiceBox<String> experienceField;
    @FXML
    private ChoiceBox<String> driverRatingField;

    private String[] experiences = {"0 лет", "1 год", "2 года", "3-4 года", "5-6 лет", "7-9 лет", "10-14 лет", "15 и более"};
    private String[] rating = {"М", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        experienceField.getItems().addAll(experiences);
        driverRatingField.getItems().addAll(rating);
    }

    public void signUpNewDriver() throws SQLException, ClassNotFoundException {
        //проверка на пустоту полей, если пустые - нельзя сохранить и предупреждение
        if (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty() ||
                passportField.getText().trim().isEmpty() || identifyCardField.getText().trim().isEmpty()) {
            checkL.setText("Проверьте, что поля не пустые!");
        } else {
            //регулярные выражения для проверки правильности введенных данных
            boolean resultFirstName = firstNameField.getText().matches("^([А-Я])([а-я]+)$");
            boolean resultLastName = lastNameField.getText().matches("^([А-Я])([а-я]+)$");
            boolean resultPatronymic = patronymicField.getText().matches("^(([А-Я])([а-я]+))|([А-я]){0}$");
            boolean resultPassport = passportField.getText().matches("^([0-9]){4}(\\s)([0-9]){6}$");
            boolean resultCard = identifyCardField.getText().matches("^([0-9]{10})$");

            //проверка, что введенная дата получения паспорта не больше текущей
            boolean resultPassportDate = DateCheck.CurrentOrFututeDate(dateOfPassportField.getValue());
            //проверка, что водитель старше 18 лет
            boolean resultBirthDate = DateCheck.Check18Years(birthDateField.getValue());

            //если все данные введены верно, то сохраняем в таблицу, иначе предупреждение о необходимости проверки данных
            if (resultFirstName && resultLastName && resultPatronymic && resultCard && resultPassport && resultPassportDate && resultBirthDate) {
                DatabaseHandler dbHandler = new DatabaseHandler();
                //запись данных из формы в переменные
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String patronymic = patronymicField.getText();
                LocalDate birthDate1 = birthDateField.getValue();
                String birthDate = birthDate1.toString();
                String passport = passportField.getText();
                LocalDate passportDate1 = dateOfPassportField.getValue();
                String passportDate = passportDate1.toString();
                String driverCardNumber = identifyCardField.getText();
                String driverExperience = experienceField.getValue();
                String driverRating = driverRatingField.getValue();
                //создаем объект класса водитель и передаем туда полученные данные
                Driver driver = new Driver(firstName, lastName, patronymic, birthDate, passport, passportDate, driverCardNumber, driverExperience, driverRating);
                dbHandler.signUpDriver(driver); //вызываем метод для записи переданного объекта в таблицу

                //обнуление полей после добавления водителя
                LocalDate date = LocalDate.now();
                firstNameField.setText("");
                lastNameField.setText("");
                patronymicField.setText("");
                birthDateField.setValue(date);
                passportField.setText("");
                dateOfPassportField.setValue(date);
                identifyCardField.setText("");
                checkL.setText("Сохранено!");
            } else {
                checkL.setText("Проверьте правильность введенных данных!");
            }
        }
    }

    private Stage stage;
    private Scene scene;


    public void switchToSceneAboutInsurer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/osago/insurerInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneRegistrationCar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/osago/carInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}