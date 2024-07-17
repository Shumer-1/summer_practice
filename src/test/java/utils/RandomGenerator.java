package utils;

import com.github.javafaker.Faker;

import java.util.Random;

/**
 * Класс для генерации случайных значений
 */
public class RandomGenerator {
    /**
     * Метод для генерации случайного целого числа из заданного диапазона
     *
     * @param from Нижняя граница диапазона
     * @param to   Верхняя граница диапазона
     * @return Случайное целое число из заданного диапазона
     */
    public static int randInt(int from, int to) {
        var random = new Random();

        return random.nextInt(from, to);
    }

    /**
     * Метод для генерации случайного
     * адреса электронной почты
     *
     * @return Случайный адрес электронной почты
     */
    public static String randomEmail() {
        var faker = new Faker();
        return faker.internet().emailAddress();
    }

    /**
     * Метод для генерации случайного пароля
     *
     * @return Случайный пароль
     */
    public static String randomPassword() {
        var faker = new Faker();
        return faker.internet().password();
    }
}
