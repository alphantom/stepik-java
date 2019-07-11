package Streams;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class MinMaxSeeker {

    public static void main(String[] args) {
        Stream stream = Arrays.stream(new Integer[]{10, 20, 1, 5, 8, 94, 1, -52, 0});
        Comparator<Integer> comparator = Integer::compare;
        BiConsumer<Integer, Integer> biConsumer = (min, max) -> {
            assertEquals(new Integer(-52), min);
            assertEquals(new Integer(94), max);
        };
        findMinMax(stream, comparator, biConsumer);
    }

    /**
     * метод, находящий в стриме минимальный и максимальный элементы в соответствии порядком,
     * заданным Comparator'ом.
     * @param stream
     * @param order
     * @param minMaxConsumer
     * @param <T>
     */
    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<? extends T> list = stream.sorted(order).collect(Collectors.toList()); // TODO :(
        if (list.size() > 0) {
            minMaxConsumer.accept(list.get(0), list.get(list.size()-1));
        } else {
            minMaxConsumer.accept(null, null);
        }
    }
}
