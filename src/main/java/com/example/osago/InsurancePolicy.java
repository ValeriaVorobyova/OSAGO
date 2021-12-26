package com.example.osago;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class InsurancePolicy {
    protected double BaseRate = 3500;
    public InsurancePolicy() {
    }
    DatabaseHandler databaseHandler = new DatabaseHandler(); //для подключения к БД

    public int getInsurerID() throws SQLException, ClassNotFoundException {
        //получаем регион машины, которую оформляем сейчас
        String query =  "SELECT * FROM " + ConstInsurer.INSURERS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int insurerID = -1;
        while (resultSet.next()) {
            insurerID = resultSet.getInt(1);
        }
        return insurerID;
    }


    public String getDate() {
        LocalDate dateCurrent = LocalDate.now();
        return dateCurrent.toString();
    }
    //Определение территориального коэффициента
    public double RegionRatio() throws SQLException, ClassNotFoundException {
        //получаем регион машины, которую оформляем сейчас
        String query =  "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String regionFromCar = "";
        while (resultSet.next()) {
            regionFromCar = resultSet.getString(6);
        }
        //подключение к таблице коэффициентов, чтобы узнать коэффициент этого региона
        String queryRatio = "SELECT * FROM region_ratio";
        ResultSet resultSet2 = statement.executeQuery(queryRatio);
        String regionFromTR = "";
        double regionRatio = -1;
        while (resultSet2.next()) {
            regionFromTR = resultSet2.getString(2);
            if (regionFromCar.equals(regionFromTR)) {
                regionRatio = resultSet2.getDouble(3);
            }
        }
        return regionRatio;
    }

    //Определение коэффициента мощности
    public double PowerRatio() throws SQLException, ClassNotFoundException {
        //получаем регион машины, которую оформляем сейчас
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String query =  "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String powerFromCar = "";
        while (resultSet.next()) {
            powerFromCar = resultSet.getString(5);
        }
        double power = Double.parseDouble(powerFromCar);
        double powerRatio;
        if (power <= 50) {
            powerRatio = 0.6;
        } else if (power > 50 && power <= 70) {
            powerRatio = 1;
        } else if (power > 70 && power <= 100) {
            powerRatio = 1.1;
        } else if (power > 100 && power <= 120) {
            powerRatio = 1.2;
        } else if (power > 120 && power <= 150) {
            powerRatio = 1.4;
        } else {
            powerRatio = 1.6;
        }
        return powerRatio;
    }

    //Коэффициент ограничения кол-ва водителей (КО)
    double CountDriversRatio() throws SQLException, ClassNotFoundException {
        //получаем
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String query =  "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String driversCount = "";
        while (resultSet.next()) {
            driversCount = resultSet.getString(7);
        }
        double driversRatio;
        if (driversCount.equals("Ограничено")) {
            driversRatio = 1;
        } else  driversRatio = 1.8;
        return driversRatio;
    }

    //определение коэффициента сезонности
    double PeriodRatio() throws SQLException, ClassNotFoundException {
        //получаем
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String query =  "SELECT * FROM " + ConstCars.CARS_TABLE;
        Statement statement = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String period = "";
        while (resultSet.next()) {
            period = resultSet.getString(9);
        }
        double periodRatio = -1;
        if (period.equals("3 месяца")) {
            periodRatio = 0.5;
        } else if (period.equals("4 месяца")) {
            periodRatio = 0.6;
        } else if (period.equals("5 месяцев")) {
            periodRatio = 0.65;
        } else if (period.equals("6 месяцев")) {
            periodRatio = 0.7;
        } else if (period.equals("7 месяцев")) {
            periodRatio = 0.8;
        } else if (period.equals("8 месяцев")) {
            periodRatio = 0.9;
        } else if (period.equals("9 месяцев")) {
            periodRatio = 0.95;
        } else periodRatio = 1;

        return periodRatio;
    }


    //пределение коэффициента возраста/стажа
    public Double ExperienceRatio() throws SQLException, ClassNotFoundException {
        //получаем
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String query =  "SELECT * FROM " + ConstDrivers.DRIVERS_TABLE;
        String queryExperience =  "SELECT * FROM experience_ratio";
        Statement statement = databaseHandler.getDbConnection().createStatement();
        Statement statement2 = databaseHandler.getDbConnection().createStatement();
        Statement statement3 = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSet resultSet2 = statement2.executeQuery(query);
        ResultSet resultSet3 = statement3.executeQuery(queryExperience);


        int id = -1;
        int currentID;
        while (resultSet2.next()) {
            id = resultSet2.getInt(2);
        }
        String experience = "";
        String minExperience = "20";
        String age = "";
        int ME;
        while (resultSet.next()) {  //определение водителя с наименьшим стажем
            experience = resultSet.getString(10);
            currentID = resultSet.getInt(2);
            ME = experience.compareTo(minExperience);
            //CompareTo возвращает положительное, если вызывающий объект больше объекта, переданного в качестве параметра;
            //отрицательное, если вызывающий объект меньше объекта, переданного в качестве параметра;
            //нуль, если объекты равны.
            if (ME < 0 && (currentID == id)) {
                minExperience = experience;
                age = resultSet.getString(6);
            }
        }
        //проверка возраста водителя
        LocalDate localDate = LocalDate.parse(age);
        int dayBD = localDate.getDayOfMonth();
        int monthBD = localDate.getMonthValue();
        int yearBD = localDate.getYear();
        LocalDate dateCurrent = LocalDate.now();
        int dayCurrent = dateCurrent.getDayOfMonth();
        int monthCurrent = dateCurrent.getMonthValue();
        int yearCurrent = dateCurrent.getYear();
        int resultAge;
        if (monthBD >= monthCurrent && dayBD >= dayCurrent) {
            resultAge = yearCurrent - yearBD - 1;
        } else  {
            resultAge = yearCurrent - yearBD;
        }

        int index;
        if (resultAge >= 16 && resultAge <= 21) index = 3;
        else if (resultAge >= 22 && resultAge <= 24) index = 4;
        else if (resultAge >= 25 && resultAge <= 29) index = 5;
        else if (resultAge >= 30 && resultAge <= 34) index = 6;
        else if (resultAge >= 35 && resultAge <= 39) index = 7;
        else if (resultAge >= 40 && resultAge <= 49) index = 8;
        else if (resultAge >= 50 && resultAge <= 59) index = 9;
        else index = 10;

        double experienceRatio = -1d;
        while (resultSet3.next()) {
            experience = resultSet3.getString(2);
            if (minExperience.equals(experience)) {
                experienceRatio = resultSet3.getDouble(index);
            }
        }

        return experienceRatio;
    }




    //определение коэффициента безаварийной езды
    public Double SafeDriveRatio() throws SQLException, ClassNotFoundException {
        double ratio; //искомый коэффициент КБМ, если число водителей неограниченно
        double maxRatio = -1; //иначе ищем максимальный КБМ среди водителей
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String queryCar = "SELECT idcars, drivers FROM " + ConstCars.CARS_TABLE;
        Statement statementCar = databaseHandler.getDbConnection().createStatement();
        ResultSet resultSetCar = statementCar.executeQuery(queryCar);
        //получение ограничено/неограничено для последнего зарег. авто
        String countOfDrivers = "";
        String carID = "";
        while (resultSetCar.next()) {
            carID = resultSetCar.getString(1); //получение id авто, для выборки водителей далее
            countOfDrivers = resultSetCar.getString(2);
        }
        if (countOfDrivers.equals("Не ограничено")) { //если неограниченно, то КБМ = 1
            ratio = 1;
            return ratio;
        } else { //иначе определение КБМ
            //для подключения к таблице водителей, сначала необходимо определить количество водителей данного авто
            String driverQuery = "SELECT idcar FROM " + ConstDrivers.DRIVERS_TABLE;
            Statement statement = databaseHandler.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(driverQuery);
            int countDriversOfThisCar = 0;
            while (resultSet.next()) {
                if (carID.equals(resultSet.getString(1))) { //если id совпадает, то увеличиваем количество водителей для данного авто
                    countDriversOfThisCar++;
                }
            }
            //двумерный массив для записи в него паспорта водителя (0), его старого рейтинга (1), количества его страховых случаев (2),
            // индекса колонки(3), нового рейтинга(4) и КБМ(5)
            String[][] driversInfo = new String[6][countDriversOfThisCar];

            //новое подключение к таблице водителей для записи информации в массив driversInfo
            String driverInfoQuery = "SELECT idcar, passport, driverRating FROM " + ConstDrivers.DRIVERS_TABLE;
            Statement statementInfo = databaseHandler.getDbConnection().createStatement();
            ResultSet resultSetInfo = statementInfo.executeQuery(driverInfoQuery);
            int i = 0;
            while (resultSetInfo.next()) {
                if (carID.equals(resultSetInfo.getString(1))) { //ищем водителей данного авто по id
                    driversInfo[0][i] = resultSetInfo.getString(2); //записываем в первую строку паспортные данные
                    driversInfo[1][i] = resultSetInfo.getString(3);//записываем во вторую строку рейтинг
                    i++;
                }
            }

            //для подключения к таблице страховых случаев
            String insuranceCaseQuery = "SELECT driverPassport, caseDate FROM " + ConstInsuranceCase.INSURANCE_CASE_TABLE;
            Statement ICstatement = databaseHandler.getDbConnection().createStatement();
            ResultSet ICresultSet = ICstatement.executeQuery(insuranceCaseQuery);
            boolean check = false;

            String driverPassportInCase; //паспортные данные из страхового случая
            int countCases = 0; //число страховых случаев у конкретного водителя
            LocalDate dateCurrent = LocalDate.now(); //текущая дата, необходимо чтобы страховые случаи были не старше 1 года
            LocalDate caseDate; //дата случая
            Period period; //для определения количества лет между текущей датой и случаем
            while (ICresultSet.next()) {
                driverPassportInCase = ICresultSet.getString(1);
                caseDate = LocalDate.parse(ICresultSet.getString(2));
                for (i = 0; i < countDriversOfThisCar; i++) {
                    if (driverPassportInCase.equals(driversInfo[0][i])) { //если паспортные данные из страхового случая и из массива данных о водителя данного авто сопадают
                        period = Period.between(caseDate, dateCurrent); //определяем сколько времени прошло между страховым случаем и сегодняшней датой
                        if (period.getYears() < 1) { //если меньше года, то
                            countCases++; //увеличиваем количество страховых случаев для данного водителя
                            driversInfo[2][i] = Integer.toString(countCases);  //записываем количество страховых случаев для данного водителя в массив
                            check = true; //проверка, что хотя бы у одного водителя данного авто были страховые случаи
                        }
                    }
                }
                countCases = 0; //обнуляем количество случаев для водителя, так как переходим к следующему страховому случаю
            }

            if(check == false) { //если ни один водитель не имеет страховых случаев, то КБМ = 1
                return 1.0;
            }
            //определение индекса колонки таблицы страховых случаев по количеству страховых случаев для каждого водителя

            int index = -1;
            for (i = 0; i < countDriversOfThisCar; i++) {
                if (driversInfo[2][i] != null) {
                    int driverInfo = Integer.parseInt(driversInfo[2][i]); //тк массив стринговый, присваиваем переменной число случаев, переведенное в int
                    if (driverInfo == 0) {
                        index = 4;
                        driversInfo[3][i] = Integer.toString(index); //записываем в массив индекс столбца для определения нового рейтинга далее
                    } else if (driverInfo == 1) {
                        index = 5;
                        driversInfo[3][i] = Integer.toString(index);
                    } else if (driverInfo == 2) {
                        index = 6;
                        driversInfo[3][i] = Integer.toString(index);
                    } else if (driverInfo == 3) {
                        index = 7;
                        driversInfo[3][i] = Integer.toString(index);
                    } else {
                        index = 8;
                        driversInfo[3][i] = Integer.toString(index);
                    }
                }
            }

            //для подключения к таблице коэффициентов для КБМ, чтобы определить новый рейтинг водителя
            String ratingQuery = "SELECT * FROM insurance_case_ratio";
            Statement ratingStatement = databaseHandler.getDbConnection().createStatement();
            ResultSet ratingResultSet = ratingStatement.executeQuery(ratingQuery);
            String lastYearRating, newRating = ""; //переменные для получения прошлогоднего рейтинга и установление нового
            while (ratingResultSet.next()) {
                lastYearRating = ratingResultSet.getString(2); //получаем вариант старого рейтинга из таблицы
                for (i = 0; i < countDriversOfThisCar; i++) {
                    if (driversInfo[1][i].equals(lastYearRating) && (driversInfo[3][i] != null)) { //если этот вариант совпадает с рейтингом из массива и были страховые случаи
                        index = Integer.parseInt(driversInfo[3][i]); //тк массив стринговый, присваиваем переменной индекс, переведенный в инт
                        driversInfo[4][i] = ratingResultSet.getString(index); //то определяем новый рейтинг на основании количества страховых случаев, индекс- номер колонки
                    }
                }
            }

            //для подключения к таблице коэффициентов КБМ еще раз
            Statement ratioStatement = databaseHandler.getDbConnection().createStatement();
            ResultSet ratioResultSet = ratioStatement.executeQuery(ratingQuery);

            //определяем КБМ на основании нового рейтинга
            while (ratioResultSet.next()) {
                lastYearRating = ratioResultSet.getString(2);
                for (i = 0; i < countDriversOfThisCar; i++) {
                    if ((driversInfo[4][i] != null) && lastYearRating.equals(driversInfo[4][i])) { //если рейтинг из массива совпадает с рейтингом из столбца и рейтинг из массива есть
                        driversInfo[5][i] = ratioResultSet.getString(3); //присваиваем этому водителю КБМ
                    }
                }
            }

            //определим максимальный КБМ среди водителей данного авто для учета в страховом полисе
            maxRatio = -1;
            for (i = 0; i < countDriversOfThisCar; i++) {  //идём по строкам
                if (driversInfo[5][i] != null) {
                    ratio = Double.parseDouble(driversInfo[5][i]);
                    if (ratio > maxRatio) {
                        maxRatio = ratio;
                    }
                }
            }
            //прежде чем возвращать коэффициент, необходимо перезаписать рейтинги водителей в таблице водителей на новые
            NewRatings(driversInfo);
        }
        return maxRatio;
    }


    void NewRatings(String[][] driversInfo) throws SQLException, ClassNotFoundException {
        //для подключения к таблице водителей
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String queryCar = "UPDATE " + ConstDrivers.DRIVERS_TABLE + " SET driverRating=? WHERE passport=?";
        PreparedStatement prST = databaseHandler.getDbConnection().prepareStatement(queryCar);

        for (int i = 0; i < driversInfo[5].length; i++) {  //идём по строкам с новыми рейтингами и паспортами
            if (driversInfo[4][i] != null) { //если рейтинг не пустой, то есть менялся, то обновляем данные
                prST.setString(1, driversInfo[4][i]); //подставляем рейтинг
                prST.setString(2, driversInfo[0][i]);//подставляем паспортные данные
                int rowsUpdated = prST.executeUpdate(); //обновляем
            }
        }
    }


    public double TotalPrice() throws SQLException, ClassNotFoundException {
        double totalPrice = BaseRate * SafeDriveRatio() * PowerRatio() * CountDriversRatio() * PeriodRatio() * ExperienceRatio() * RegionRatio();
        totalPrice = Math.round(totalPrice * 100);
        totalPrice = totalPrice/100;
        return totalPrice;
    }
}
