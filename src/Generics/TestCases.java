package Generics;

import java.util.Optional;

public class TestCases {

    public static void main(String[] args) {
        Example<String> example = new Example<>();
        example.someMethod(new Object());
    }

    public static class Example<X> {
//        private static X var0; WRONG
        public void someMethod(Object obj) {

            X var1 = (X) obj;
            Optional<X> var3 = Optional.empty();
//            X[] array = new X[10]; WRONG
//            if (obj instanceof Optional<X>) WRONG
//            if (obj instanceof X) WRONG
//            X var2 = new X(); WRONG
        }
    }
}
