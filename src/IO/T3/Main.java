package IO.T3;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static final String CHARSET = "UTF-8";
    /**
     * читающую текст из System.in и выводящую в System.out сумму всех встреченных в тексте
     * вещественных чисел с точностью до шестого знака после запятой.
     * Числом считается последовательность символов, отделенная от окружающего
     * текста пробелами или переводами строк и успешно разбираемая методом Double.parseDouble
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("[\\n ]").useLocale(Locale.forLanguageTag("en"));
        Double total = 0d;
        while(scanner.hasNext()) {
            try {
                Double nextDouble = scanner.nextDouble();
                total += nextDouble;
            } catch (InputMismatchException e) {
                scanner.next();
                continue;
            }
        }
        System.out.printf("%.6f", total);
    }
}
