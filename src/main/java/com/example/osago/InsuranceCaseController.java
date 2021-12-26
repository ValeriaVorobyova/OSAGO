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

public class InsuranceCaseController {
    @FXML
    private DatePicker dayOfCaseField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField paymentField;
    @FXML
    private TextField passportField;
    @FXML
    private TextField patronymicField;
    @FXML
    private TextField carNumberField;
    @FXML
    private Label checkL;

    public void signUpNewCase() throws SQLException, ClassNotFoundException {
        if (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty() ||
                patronymicField.getText().trim().isEmpty() || passportField.getText().trim().isEmpty() ||
                paymentField.getText().trim().isEmpty() || carNumberField.getText().trim().isEmpty() ||
                descriptionField.getText().trim().isEmpty()) {
            checkL.setText("Проверьте, что поля не пустые!");
        } else {
            //регулярные выражения для проверки правильности введенных данных
            boolean resultFirstName = firstNameField.getText().matches("^([А-Я])([а-я]+)$");
            boolean resultLastName = lastNameField.getText().matches("^([А-Я])([а-я]+)$");
            boolean resultPatronymic = patronymicField.getText().matches("^(([А-Я])([а-я]+))|([А-я]){0}$");
            boolean resultPayment = paymentField.getText().matches("^[1-9]{1}[0-9]{1,5}[.,]?[0-9]{1,2}$");
            boolean resultPaymentCheck = Double.parseDouble(paymentField.getText()) <= 500000 ? true : false; //проверка, что выплата не привышает максимальную в ОСАГО
            boolean resultPassport = passportField.getText().matches("^([0-9]){4}(\\s)([0-9]){6}$");
            boolean resultNumber = carNumberField.getText().matches("^([А-Я]{1}[0-9]{3}[А-Я]{2})$");
            //проверка, что введенная дата не больше текущей
            boolean resultDate = DateCheck.CurrentOrFututeDate(dayOfCaseField.getValue());

            //если все данные введены верно, то сохраняем в таблицу, иначе предупреждение о необходимости проверки данных
            if (resultFirstName && resultLastName && resultPatronymic && resultPayment && resultNumber && resultPassport && resultDate && resultPaymentCheck) {
                DatabaseHandler dbHandler = new DatabaseHandler();
                //запись данных из формы в переменные
                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
                String patronymic = patronymicField.getText();
                String passport = passportField.getText();
                Double payment = Double.parseDouble(paymentField.getText());
                String caseDate = (dayOfCaseField.getValue()).toString();
                String carNumber = carNumberField.getText();
                String caseDescription = descriptionField.getText();
                //создаем объект класса для страхового случая и передаем туда полученные данные
                InsuranceCase insuranceCase = new InsuranceCase(firstName, lastName, patronymic, passport, payment, caseDate, carNumber, caseDescription);
                dbHandler.signUpInsuranceCase(insuranceCase); //вызываем метод для записи переданного объекта в таблицу

                //обнуление полей после добавления водителя
                LocalDate date = LocalDate.now();
                firstNameField.setText("");
                paymentField.setText("");
                lastNameField.setText("");
                patronymicField.setText("");
                dayOfCaseField.setValue(date);
                passportField.setText("");
                carNumberField.setText("");
                descriptionField.setText("");
                checkL.setText("Сохранено!");
            } else {
                checkL.setText("Проверьте правильность введенных данных!");
            }
        }
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
