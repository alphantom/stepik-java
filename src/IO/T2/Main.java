package IO.T2;

import java.io.IOException;

public class Main {

    /**
     * Требуется заменить все вхождения пары символов '\r'(13) и '\n'(10) на один символ '\n'(10).
     * Если на входе встречается одиночный символ '\r', за которым не следует '\n',
     * то символ '\r' выводится без изменения.
     *
     * @param args
     */
    public static void main(String... args) {
        int prev, current;
        try {
            prev = System.in.read();
            while (prev != -1) {
                current = System.in.read();
                if (!(prev == 13 && current == 10)) {
                    System.out.write(prev);
                }
                prev = current;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.flush();
    }
}
