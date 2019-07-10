package Collections;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    /**
     * Читает из System.in последовательность целых чисел, разделенных пробелами,
     * затем удаляет из них все числа, стоящие на чётных позициях,
     * и затем выводит получившуюся последовательность в обратном порядке в System.out.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\\s");
        LinkedList<Integer> list = new LinkedList<>();
        int i = 0;
        while (scanner.hasNext()) {
            if (i % 2 == 0) {
                scanner.nextInt();
            } else {
                list.add(scanner.nextInt());
            }
            i++;
        }
        while(list.size() > 0) {
            System.out.print(list.pollLast());
            if (list.size() > 0) System.out.print(" ");
        }
    }
}
