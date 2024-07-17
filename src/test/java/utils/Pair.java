package utils;

/**
 * Запись для хранения пары элементов произвольных типов
 *
 * @param first  Первый элемент пары
 * @param second Второй элемент пары
 * @param <T>    Тип первого элемента
 * @param <S>    Тип второго элемента
 */
public record Pair<T, S>(T first, S second) {
}
