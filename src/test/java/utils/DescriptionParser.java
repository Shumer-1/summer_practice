package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Класс для обработки описания элемента страницы
 */
public class DescriptionParser {
    /**
     * Метод для обработки описания элемента страницы
     *
     * @param str Строка, содержащая описание элемента
     * @return Хэш-мап, хранящая значения всех пунктов описания
     * @throws Exception Не удалось обработать описание
     */
    public static HashMap<String, String> parse(String str) throws Exception {
        var description = new HashMap<String, String>();

        str = str.replaceAll("<.*? |>.*|\"", "");

        var parts = new ArrayList<String>();

        for (var chunk : str.split(" ")) {
            parts.addAll(List.of(chunk.split("=", 2)));

            if (parts.size() < 2) {
                parts.clear();
                parts.addAll(List.of(chunk.split(":", 2)));

                if (parts.size() < 2) {
                    throw new Exception("Incorrect description");
                }
            }

            description.put(parts.get(0), parts.get(1));
            parts.clear();
        }

        return description;
    }
}
