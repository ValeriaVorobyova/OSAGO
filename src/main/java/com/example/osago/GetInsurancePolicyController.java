package com.example.osago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetInsurancePolicyController  {
    @FXML
    private Label carBrandField;
    @FXML
    private Label carModelField;
    @FXML
    private Label carNumberField;
    @FXML
    private Label carYearField;
    @FXML
    private Label driverCountField;
    @FXML
    private TableView<DriversInInsurancePolicy> driversTable;
    @FXML
    private TableColumn<DriversInInsurancePolicy, String> lastNameT;
    @FXML
    private TableColumn<DriversInInsurancePolicy, String> firstNameT;
    @FXML
    private TableColumn<DriversInInsurancePolicy, String> patronymicT;
    @FXML
    private TableColumn<DriversInInsurancePolicy, String> cardNumberT;
    @FXML
    private Label firstNameField;
    @FXML
    private Label fromDateField;
    @FXML
    private Label toDateField;
    @FXML
    private Label lastNameField;
    @FXML
    private Label patronymicField;
    @FXML
    private Label policyNumberField;
    @FXML
    private TextField carNumberForSearch;
    @FXML
    private TableView<RatiosTable> ratiosTable;
    @FXML
    private TableColumn<RatiosTable, String> baseRateT;
    @FXML
    private TableColumn<RatiosTable, String> countDriversRatioT;
    @FXML
    private TableColumn<RatiosTable, String> experienceRatioT;
    @FXML
    private TableColumn<RatiosTable, String> periodRatio;
    @FXML
    private TableColumn<RatiosTable, String> powerRatioT;
    @FXML
    private TableColumn<RatiosTable, String> regionRatioT;
    @FXML
    private TableColumn<RatiosTable, String> safeDriveRatioT;
    @FXML
    private TableColumn<RatiosTable, String> totalPrice;


    //перемещение на главную форму
    private Stage stage;
    private Scene scene;
    DatabaseHandler databaseHandler = new DatabaseHandler();
    public void switchToSceneHomepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/osago/homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    ObservableList<DriversInInsurancePolicy> oblist = FXCollections.observableArrayList(); //коллекция для записи в таблицу на форму объектов - водителей, вписанных в полюс
    ObservableList<RatiosTable> oblistRatios = FXCollections.observableArrayList(); //коллекция для записи в таблицу на форме коэффициентов


    public void initialization() {
        //вывод в таблицу водителей, у которых есть доступ к управлению
        String query = "SELECT * FROM " + ConstDrivers.DRIVERS_TABLE;  //строка с запросом к таблице водителей
        try {
            ResultSet resultSet = databaseHandler.getDbConnection().createStatement().executeQuery(query); //подключение к таблице водителей
            int id = getIDCar(); //получение id введенного авто
            int id2;
            while (resultSet.next()) {
                id2 = resultSet.getInt(2);
                if (id == id2) { //если id авто совпадает с id авто, номер которого ввели, в коллекцию добавляется новый объект с данными из колонок таблицы
                    oblist.add(new DriversInInsurancePolicy(resultSet.getString(4), resultSet.getString(3),
                            resultSet.getString(5), resultSet.getString(9)));

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        lastNameT.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameT.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        patronymicT.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        cardNumberT.setCellValueFactory(new PropertyValueFactory<>("driverCardNumber"));
        driversTable.setItems(oblist);


        //вывод коэффициентов в таблицу
        String queryRatios = "SELECT * FROM " + ConstInsurancePolicy.INSURANCE_POLICY_TABLE;
        try {
            ResultSet rsRatio = databaseHandler.getDbConnection().createStatement().executeQuery(queryRatios);
            int id = getIDCar(); //получение id введенного авто
            int id2;
            String date = "";
            String policyNumber = "";
            while (rsRatio.next()) {
                id2 = rsRatio.getInt(3);
                if (id == id2) {  //если id авто совпадает с id авто, номер которого ввели, в коллекцию добавляется новый объект с данными из колонок
                    oblistRatios.add(new RatiosTable(rsRatio.getString(4), rsRatio.getString(9),
                            rsRatio.getString(8), rsRatio.getString(10), rsRatio.getString(5),
                            rsRatio.getString(6), rsRatio.getString(7), rsRatio.getString(11)));
                    policyNumber = Integer.toString(rsRatio.getInt(1));
                    date = rsRatio.getString(12);
                }
            }
            policyNumberField.setText(policyNumber); //устанавливаем значение на форме - номера полиса
            fromDateField.setText(date);//устанавливаем значение на форме - дату полиса
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //устанавливаем значение коэффициентов в таблицу на форме
        baseRateT.setCellValueFactory(new PropertyValueFactory<>(ConstInsurancePolicy.BASE_RATIO));
        safeDriveRatioT.setCellValueFactory(new PropertyValueFactory<>(ConstInsurancePolicy.SAFE_DRIVE_RATIO));
        experienceRatioT.setCellValueFactory(new PropertyValueFactory<>(ConstInsurancePolicy.EXPERIENCE_RATIO));
        regionRatioT.setCellValueFactory(new PropertyValueFactory<>(ConstInsurancePolicy.REGION_RATIO));
        powerRatioT.setCellValueFactory(new PropertyValueFactory<>(ConstInsurancePolicy.POWER_RATIO));
        countDriversRatioT.setCellValueFactory(new PropertyValueFactory<>(ConstInsurancePolicy.COUNT_DRIVERS_RATIO));
        periodRatio.setCellValueFactory(new PropertyValueFactory<>(ConstInsurancePolicy.PERIOD_RATIO));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>(ConstInsurancePolicy.TOTAL_PRICE));
        ratiosTable.setItems(oblistRatios);

        try {
            getInsurer();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            getCarInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  //метод получения данных о страхователе для вывода в поля на форме
    public void getInsurer() throws SQLException, ClassNotFoundException {
        //подключение к таблице страхователей
        String query = "SELECT * FROM " + ConstInsurer.INSURERS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String lastName = "", firstName = "", patronymic = "";
        int carID = getIDCar(); //получение id введенного авто
        int id;
        while (resultSet.next()) {
            id = resultSet.getInt(2);
            if (carID == id) {   //если id авто совпадает с id авто, номер которого ввели, в коллекцию добавляется новый объект с данными из колон
                lastName = resultSet.getString(4);
                firstName = resultSet.getString(3);
                patronymic = resultSet.getString(5);
            }
        }
        lastNameField.setText(lastName);
        firstNameField.setText(firstName);
        patronymicField.setText(patronymic);
    }

    //метод получения данных об авто для вывода в поля на форме
    public void getCarInfo() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String brand = "", model = "", year = "", number = "", driversCount = "", period = "";
        int carID = getIDCar(); //получение id введенного авто
        int id;
        while (resultSet.next()) {
            id = resultSet.getInt(1);
            if (carID == id) { // //если id авто совпадает с id авто, номер которого ввели, в коллекцию добавляется новый объект с данными из колон
                year = resultSet.getString(2);
                brand = resultSet.getString(3);
                model = resultSet.getString(4);
                number = resultSet.getString(10);
                driversCount = resultSet.getString(7);
                period = resultSet.getString(9);
            }
        }
        carBrandField.setText(brand);
        carModelField.setText(model);
        carYearField.setText(year);
        carNumberField.setText(number);
        toDateField.setText(period);
        if (driversCount.equals("Ограничено")) {
            driverCountField.setText("лиц, допущенных к управлению транспортным средством");
        } else if (driversCount.equals("Не ограничено")) {
            driverCountField.setText("неограниченного количества лиц, допущенных к управлению транспортным средством");
        }

    }

    //метод получения id авто, номер которого ввели
    public int getIDCar() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int id = -1;
        String number;
        while (resultSet.next()) {
            number = resultSet.getString(10);
            if (number.equals(carNumberForSearch.getText())) { //если введенный номер совпадает с номером из таблице, получаем id
                id = resultSet.getInt(1);
            }
        }
        return id;
    }
}

