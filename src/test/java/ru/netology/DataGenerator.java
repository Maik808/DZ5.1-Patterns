package ru.netology;

import com.github.javafaker.Faker;
import lombok.*;

import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class DataGenerator {
    private DataGenerator() {
    }

    @Value
    public static class CorrectInformation {
        String city;
        String firstName;
        String lastName;
        String PhoneNumber;
    }

    public static CorrectInformation Correct() {
        Faker faker = new Faker(new Locale("ru"));
        return new CorrectInformation(faker.address().city(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.phoneNumber().cellPhone()
        );
    }

    @Value
    public static class NotCorrectInformation {
        String notCorrectName;
        String notCorrectPhone;
        String notCorrectCity;
        String notCorrectData;
    }

    public static NotCorrectInformation NotCorrect() {
        return new NotCorrectInformation("Vasin234", "37", "Kostroma", "12122000");
    }

    public static String forwardDate(int plusDays) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate newDate = today.plusDays(plusDays);
        return formatter.format(newDate);
    }

    public static String makeCity() {
        String[] myCityList = new String[]{"Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Владикавказ", "Горно-Алтайск", "Йошкар-Ола", "Казань", "Калининград", "Калуга", "Краснодар", "Магадан", "Магас", "Махачкала", "Нарьян-Мар", "Салехард", "Самара", "Саранск", "Саратов", "Хабаровск", "Ханты-Мансийск", "Южно-Сахалинск", "Великий Новгород", "Владивосток", "Владимир", "Вологда", "Рязань", "Биробиджан", "Чебоксары", "Москва", "Санкт-Петербург", "Ульяновск", "Симферополь", "Ростов-на-Дону"};
        int city = (int) Math.floor(Math.random() * myCityList.length);
        return myCityList[city];
    }

}
