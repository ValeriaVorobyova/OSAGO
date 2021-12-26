package com.example.osago;

import java.sql.*;

//здесь подключаемся к БД, записываем в нее и читаем из нее данные
public class DatabaseHandler extends Configs {

    //подключение к БД
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    //метод записи нового автомобиля в таблицу автомобилей
    public void signUpCar(Car car) throws SQLException, ClassNotFoundException {
        //Строка запроса для добавления в таблицу в определенные столбцы данные, которые будут подставлены вместо знаков вопроса
        String insert = "INSERT INTO " + ConstCars.CARS_TABLE + "(" + ConstCars.CARS_YEAR + "," +
                ConstCars.CARS_BRAND + "," + ConstCars.CARS_MODEL + "," + ConstCars.CARS_POWER + "," + ConstCars.CARS_REGION +
                "," + ConstCars.CARS_DRIVERS + "," + ConstCars.CARS_VIN + "," + ConstCars.CARS_PERIOD + "," + ConstCars.CARS_NUMBER + ")" + "VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert); //устанавливаем соединение с бд и таблицей, передав запрос
            //вызываем геттеры у переданного объекта - автомобиля для получения и записи данных
            prST.setString(1, car.getYear());
            prST.setString(2, car.getBrand());
            prST.setString(3, car.getModel());
            prST.setString(4, car.getPower());
            prST.setString(5, car.getRegion());
            prST.setString(6, car.getDrivers());
            prST.setString(7, car.getVIN());
            prST.setString(8, car.getPeriod());
            prST.setString(9, car.getNumber());
            prST.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка данных авто!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка данных авто!");
            e.printStackTrace();
        }
    }

    //метод записи нового водителя в таблицу водителей
    public void signUpDriver(Driver driver) throws SQLException, ClassNotFoundException {
        //Строка запроса для добавления в таблицу в определенные столбцы данные, которые будут подставлены вместо знаков вопроса
        String insert = "INSERT INTO " + ConstDrivers.DRIVERS_TABLE + "(" + ConstDrivers.CAR_ID + "," +
                ConstDrivers.FIRST_NAME + "," + ConstDrivers.LAST_NAME + "," + ConstDrivers.PATRONYMIC + "," + ConstDrivers.BIRTH_DATE +
                "," + ConstDrivers.PASSPORT + "," + ConstDrivers.PASSPORT_DATE + "," + ConstDrivers.DRIVER_CARD + "," + ConstDrivers.DRIVER_EXPERIENCE + "," + ConstDrivers.DRIVER_RATING + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        //вызываем геттеры у переданного объекта - водителя для получения и записи данных
        try {

            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setInt(1, getIDCar()); //получаем id авто из таблицы авто для связи между таблицами
            prST.setString(2, driver.getFirstName());
            prST.setString(3, driver.getLastName());
            prST.setString(4, driver.getPatronymic());
            prST.setString(5, driver.getBirthDate());
            prST.setString(6, driver.getPassport());
            prST.setString(7, driver.getPassportDate());
            prST.setString(8, driver.getDriverCardNumber());
            prST.setString(9, driver.getDriverExperience());
            prST.setString(10, driver.getDriverRating());
            prST.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка данных водителя!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка данных водителя!");
            e.printStackTrace();
        }
    }

    //метод записи нового страхователя в таблицу страхователей
    public void signUpInsurer(Insurer insurer) throws SQLException, ClassNotFoundException {
        //Строка запроса для добавления в таблицу в определенные столбцы данные, которые будут подставлены вместо знаков вопроса
        String insert = "INSERT INTO " + ConstInsurer.INSURERS_TABLE + "(" + ConstInsurer.CAR_ID + "," +
                ConstInsurer.FIRST_NAME + "," + ConstInsurer.LAST_NAME + "," + ConstInsurer.PATRONYMIC + "," + ConstInsurer.BIRTH_DATE +
                "," + ConstInsurer.PASSPORT + "," + ConstInsurer.PASSPORT_DATE + "," + ConstInsurer.PASSPORT_DEPARTMENT + ")" + "VALUES(?,?,?,?,?,?,?,?)";
        //вызываем геттеры у переданного объекта - страхователя для получения и записи данных
        try {

            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setInt(1, getIDCar()); //получаем id авто из таблицы авто для связи между таблицами
            prST.setString(2, insurer.getFirstName());
            prST.setString(3, insurer.getLastName());
            prST.setString(4, insurer.getPatronymic());
            prST.setString(5, insurer.getBirthDate());
            prST.setString(6, insurer.getPassport());
            prST.setString(7, insurer.getPassportDate());
            prST.setString(8, insurer.getPassportDepartment());
            prST.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        InsurancePolicy insurancePolicy = new InsurancePolicy();
        signUpInsurancePolicy(insurancePolicy);
    }

    //метод записи нового страхового полюса в таблицу страховых полюсов
    public void signUpInsurancePolicy(InsurancePolicy insurancePolicy) throws SQLException, ClassNotFoundException {
        //Строка запроса для добавления в таблицу в определенные столбцы данные, которые будут подставлены вместо знаков вопроса
        String insert = "INSERT INTO " + ConstInsurancePolicy.INSURANCE_POLICY_TABLE + "(" + ConstInsurancePolicy.INSURER_ID + "," +
                ConstInsurancePolicy.CAR_ID + "," + ConstInsurancePolicy.BASE_RATIO + "," + ConstInsurancePolicy.POWER_RATIO
                + "," + ConstInsurancePolicy.COUNT_DRIVERS_RATIO + "," + ConstInsurancePolicy.PERIOD_RATIO + ","
                + ConstInsurancePolicy.EXPERIENCE_RATIO + "," + ConstInsurancePolicy.SAFE_DRIVE_RATIO + "," + ConstInsurancePolicy.REGION_RATIO + "," + ConstInsurancePolicy.TOTAL_PRICE + "," +
                ConstInsurancePolicy.DATE + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        //вызываем геттеры у переданного объекта - страхового полюса для получения и записи данных
        try {

            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setInt(1, insurancePolicy.getInsurerID());
            prST.setInt(2, getIDCar()); //получаем id авто из таблицы авто для связи между таблицами
            prST.setDouble(3, insurancePolicy.BaseRate);
            prST.setDouble(4, insurancePolicy.PowerRatio());
            prST.setDouble(5, insurancePolicy.CountDriversRatio());
            prST.setDouble(6, insurancePolicy.PeriodRatio());
            prST.setDouble(7, insurancePolicy.ExperienceRatio());
            prST.setDouble(8, insurancePolicy.SafeDriveRatio());
            prST.setDouble(9, insurancePolicy.RegionRatio());
            prST.setDouble(10, insurancePolicy.TotalPrice());
            prST.setString(11, insurancePolicy.getDate());
            prST.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //метод записи нового страхового случая в таблицу страховых случаев
    public void signUpInsuranceCase(InsuranceCase insuranceCase) throws SQLException, ClassNotFoundException {
        //Строка запроса для добавления в таблицу в определенные столбцы данные, которые будут подставлены вместо знаков вопроса
        String insert = "INSERT INTO " + ConstInsuranceCase.INSURANCE_CASE_TABLE + "(" + ConstInsuranceCase.DRIVER_LAST_NAME + "," + ConstInsuranceCase.DRIVER_FIRST_NAME +
                "," + ConstInsuranceCase.DRIVER_PATRONYMIC + "," + ConstInsuranceCase.DRIVER_PASSPORT + "," + ConstInsuranceCase.DRIVER_PAYMENT + "," + ConstInsuranceCase.DRIVER_CASE_DATE +
                "," + ConstInsuranceCase.DRIVER_CAR_NUMBER + "," + ConstInsuranceCase.DRIVER_CASE_DESCRIPTION + ")" + "VALUES(?,?,?,?,?,?,?,?)";
        //вызываем геттеры у переданного объекта - страхового случая для получения и записи данных
        try {

            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setString(1, insuranceCase.getDriverLastName());
            prST.setString(2, insuranceCase.getDriverFirstName());
            prST.setString(3, insuranceCase.getDriverPatronymic());
            prST.setString(4, insuranceCase.getDriverPassport());
            prST.setDouble(5, insuranceCase.getPayment());
            prST.setString(6, insuranceCase.getCaseDate());
            prST.setString(7, insuranceCase.getCarNumber());
            prST.setString(8, insuranceCase.getCaseDescription());
            prST.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //получение id автомобиля, страховка которого сечас оформляется
    public int getIDCar() throws SQLException, ClassNotFoundException {
        //подключаемся к таблице автомобилей
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String query = "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int id = -1;
        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;
    }
}
