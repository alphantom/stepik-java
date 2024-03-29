package Collections;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static Collections.SymmetricDifferenceTask.symmetricDifference;
import static junit.framework.TestCase.assertEquals;

public class SymmetricDifferenceTest {

    @Test(timeout = 8000)
    public void symmetricDifferenceSample1() throws Throwable {
        Set<Integer> set1 = createSet(1, 2, 3);
        HashSet<Integer> backupSet1 = new HashSet<>(set1);

        Set<Integer> set2 = createSet(0, 1, 2);
        HashSet<Integer> backupSet2 = new HashSet<>(set2);

        Set<Integer> expectedSet = createSet(0, 3);

        Set actual = symmetricDifference(set1, set2);

        assertEquals("set1 read only", backupSet1, set1);
        assertEquals("set2 read only", backupSet2, set2);

        String message = String.format("SymmetricDifferenceTask.symmetricDifference(%s, %s)", set1.toString(), set2.toString());
        assertEquals(message, expectedSet, actual);
    }

    @Test(timeout = 8000)
    public void symmetricDifferenceSample2() throws Throwable {
        Set<Integer> set1 = createSet(1, 2, 3);
        HashSet<Integer> backupSet1 = new HashSet<>(set1);

        Set<Integer> set2 = createSet(4, 5, 6);
        HashSet<Integer> backupSet2 = new HashSet<>(set2);

        Set<Integer> expectedSet = createSet(1, 2, 3, 4, 5, 6);

        Set actual = symmetricDifference(set1, set2);

        assertEquals("set1 read only", backupSet1, set1);
        assertEquals("set2 read only", backupSet2, set2);

        String message = String.format("SymmetricDifferenceTask.symmetricDifference(%s, %s)", set1.toString(), set2.toString());
        assertEquals(message, expectedSet, actual);
    }

    @Test(timeout = 8000)
    public void symmetricDifferenceEmptySets() throws Throwable {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        Set<Integer> expectedSet = new HashSet<>();

        Set actual = symmetricDifference(set1, set2);

        assertEquals("set1 read only", expectedSet, set1);
        assertEquals("set2 read only", expectedSet, set2);

        String message = String.format("SymmetricDifferenceTask.symmetricDifference(%s, %s)", set1.toString(), set2.toString());
        assertEquals(message, expectedSet, actual);
    }

    @Test(timeout = 8000)
    public void symmetricDifferenceEqualsSets() throws Throwable {
        Set<Integer> set1 = createSet(1, 2, 3);
        HashSet<Integer> backupSet1 = new HashSet<>(set1);

        HashSet<Integer> set2 = new HashSet<>(set1);
        HashSet<Integer> backupSet2 = new HashSet<>(set2);

        Set<Integer> expectedSet = new HashSet<>();

        Set actual = symmetricDifference(set1, set2);

        assertEquals("set1 read only", backupSet1, set1);
        assertEquals("set2 read only", backupSet2, set2);

        String message = String.format("SymmetricDifferenceTask.symmetricDifference(%s, %s)", set1.toString(), set2.toString());
        assertEquals(message, expectedSet, actual);
    }

    @SafeVarargs
    private final <T> Set<T> createSet(T... items) {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, items);
        return set;
    }
}
