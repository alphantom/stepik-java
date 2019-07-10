package Collections;

import java.util.HashSet;
import java.util.Set;

public class SymmetricDifferenceTask {

    /**
     * Возвращает результат в виде нового множества.
     * Изменять переданные в него множества не допускается
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> set1copy = new HashSet<>(set1);
        Set<T> set2copy = new HashSet<>(set2);
        set1copy.removeAll(set2);
        set2copy.removeAll(set1);
        set1copy.addAll(set2copy);

        return set1copy;
    }

}
