package FunctionalInterfaces;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfacesTask {

    public static void main(String[] args) {

        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = str -> condition.test(str) ? ifTrue.apply(str) : ifFalse.apply(str);
    }
}
