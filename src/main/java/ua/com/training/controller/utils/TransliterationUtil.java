package ua.com.training.controller.utils;

public class TransliterationUtil {
    private static final char[] ua = {' ', 'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'і', 'и', 'й', 'ї', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Й', 'Ї', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я'};
    private static final String[] en = {" ", "a", "b", "v", "h", "g", "d", "e", "ie", "zh", "z", "i", "y", "i", "i", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "ju", "ja", "A", "B", "V", "H", "G", "D", "E", "Ye", "Zh", "Z", "Y", "I", "Y", "Yi", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "H", "Ts", "Ch", "Sh", "Sch", "", "Ju", "Ja"};

    public static String transliterateUaToEn(String name) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            for (int x = 0; x < ua.length; x++) {
                if (name.charAt(i) == ua[x]) {
                    builder.append(en[x]);
                }
            }
        }
        return builder.toString();
    }
}
