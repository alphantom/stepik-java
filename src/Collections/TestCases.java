package Collections;

import java.util.ArrayList;
import java.util.Collection;

public class TestCases {

    public static void main(String[] args) {
        // ? - collection has been parametrized but we don't know what type in there
        Collection<?> collection = new ArrayList<>();
        Object object = new Object();

//        collection.add(object); WRONG
        collection.size();
        collection.toArray();
        collection.remove(object);
        collection.clear();
//        collection.addAll(Arrays.asList(object)); WRONG
        collection.iterator();
        collection.contains(object);
    }
}
