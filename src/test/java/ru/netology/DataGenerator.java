package ru.netology;


import com.github.javafaker.Faker;
import java.util.Locale;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class DataGenerator {
    private Faker faker = new Faker(new Locale("ru"));
    private LocalDate today = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String forwardDate(int plusDays) {
        LocalDate newDate = today.plusDays(plusDays);
        return formatter.format(newDate);
    }

    public String makeCity() {
        String[] myCityList = new String[]{"Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Владикавказ", "Горно-Алтайск", "Йошкар-Ола", "Казань", "Калининград", "Калуга", "Краснодар", "Магадан", "Магас", "Махачкала", "Нарьян-Мар", "Салехард", "Самара", "Саранск", "Саратов", "Хабаровск", "Ханты-Мансийск", "Южно-Сахалинск", "Великий Новгород", "Владивосток", "Владимир", "Вологда", "Рязань", "Биробиджан", "Чебоксары", "Москва", "Санкт-Петербург", "Ульяновск", "Симферополь", "Ростов-на-Дону"};
        int city = (int) Math.floor(Math.random() * myCityList.length);
        return myCityList[city];
    }

    String city = faker.address().city();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String PhoneNumber = faker.phoneNumber().cellPhone();
}
