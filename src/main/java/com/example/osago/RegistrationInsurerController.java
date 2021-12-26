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
import java.sql.SQLException;
import java.time.LocalDate;

public class RegistrationInsurerController {


    @FXML
    private DatePicker birthDateField;

    @FXML
    private DatePicker dateOfPassportField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;


    @FXML
    private TextField passportDepartmentField;
    @FXML
    private Label checkL;

    @FXML
    private TextField passportField;

    @FXML
    private TextField patronymicField;


    public void signUpNewInsurer() throws SQLException, ClassNotFoundException {
        //проверка на пустоту полей, если пустые - нельзя сохранить и предупреждение
        if (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty() ||
                passportField.getText().trim().isEmpty() || passportDepartmentField.getText().trim().isEmpty()) {
            checkL.setText("Проверьте, что поля не пустые!");
        } else {
            //регулярные выражения для проверки правильности введенных данных
            boolean resultFirstName = firstNameField.getText().matches("^([А-Я])([а-я]+)$");
            boolean resultLastName = lastNameField.getText().matches("^([А-Я])([а-я]+)$");
            boolean resultPatronymic = patronymicField.getText().matches("^(([А-Я])([а-я]+))|([А-я]){0}$");
            boolean resultPassport = passportField.getText().matches("^([0-9]){4}(\\s)([0-9]){6}$");

            //проверка, что введенная дата получения паспорта не больше текущей
            boolean resultPassportDate = DateCheck.CurrentOrFututeDate(dateOfPassportField.getValue());
            //проверка, что страхователь старше 18 лет
            boolean resultBirthDate = DateCheck.Check18Years(birthDateField.getValue());

            //если все данные введены верно, то сохраняем в таблицу, иначе предупреждение о необходимости проверки данных
            if (resultFirstName && resultLastName && resultPatronymic && resultPassport && resultPassportDate && resultBirthDate) {
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
                String passportDepartment = passportDepartmentField.getText();
                //создаем объект класса страхователь и передаем туда полученные данные
                Insurer insurer = new Insurer(1, firstName, lastName, patronymic, birthDate, passport, passportDate, passportDepartment);
                dbHandler.signUpInsurer(insurer); //вызываем метод для записи переданного объекта в таблицу

                //обнуление полей после добавления страхователя
                checkL.setText("Сохранено!");
            } else {
                checkL.setText("Проверьте правильность введенных данных!");
            }
        }
    }


    private Stage stage;
    private Scene scene;

    public void switchToSceneAboutDriver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/osago/driverInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneAboutInsurancePolicy(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/osago/insurancePolicy.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
