package com.example.osago;
import java.time.LocalDate;


public class DateCheck {
//проверка, что введенная дата меньше текущей
    public static boolean CurrentOrFututeDate(LocalDate date){
        LocalDate todayDate = LocalDate.now();

        //день месяц год для текущей даты
        int dayToday = todayDate.getDayOfMonth();
        int monthToday = todayDate.getMonthValue();
        int yearToday = todayDate.getYear();

        //день месяц год для полученной даты
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        if (yearToday > year) { //если текущей год больше введенного, сразу возвращаем true
            return true;
        } else if (yearToday == year) { //если года равны, сравниваем месяцы
            if (monthToday > month) return true; //если текущей месяц больше введенного, сразу возвращаем true
            else if (monthToday == month) { //если месяцы равны, сравниваем дни
                if (dayToday >= day) return true; // если текущий день больше или равен введенному, то возвращаем true
                else return false;
            }
            else return false;  //если текущей месяц меньше введенного, сразу возвращаем false
        }
        else { //если текущей год меньше введенного, сразу возвращаем false
            return false;
        }
    }
//проверка, что клиенту минимум 18 лет
    public static boolean Check18Years(LocalDate date){
        LocalDate todayDate = LocalDate.now();

        //день месяц год для текущей даты
        int dayToday = todayDate.getDayOfMonth();
        int monthToday = todayDate.getMonthValue();
        int yearToday = todayDate.getYear();

        //день месяц год для даты рождения
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        if (yearToday - year > 18) { //если текущей год больше введенного более чем на 18 лет, сразу возвращаем true
            return true;
        } else if (yearToday - year == 18) { //если текущей год больше введенного ровно на 18 лет, то проверяем месяцы
            if (monthToday > month) return true; //если текущей месяц больше введенного, сразу возвращаем true
            else if (monthToday == month) { //если месяцы равны, сравниваем дни
                if (dayToday > day) return true; // если текущий день больше введенного, то возвращаем true
                else return false;
            }
            else return false;  //если текущей месяц меньше введенного, сразу возвращаем false
        }
        else { //если текущей год меньше введенного, сразу возвращаем false
            return false;
        }
    }
}
