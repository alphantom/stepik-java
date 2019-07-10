package Generics;


import java.util.Objects;

public class PairTask {

    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        System.out.printf("Is it correct? %s%n", mustBeTrue && mustAlsoBeTrue ? "Yes" : "No :(" );
    }

    /**
     * Generic-класс Pair, похожий на Optional, но содержащий пару элементов разных типов
     * и не запрещающий элементам принимать значение null.
     *
     * Реализуйте методы getFirst(), getSecond(), equals() и hashCode(),
     * а также статический фабричный метод of().
     * Конструктор должен быть закрытым.
     * @param <K>
     * @param <V>
     */
    static class Pair<K, V> {

        private final K key;
        private final V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        static <K, V> Pair<K, V> of(K key, V value) {
            return new Pair<>(key, value);
        }

        K getFirst() {
            return key;
        }

        V getSecond() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Pair<K, V> pair = (Pair<K, V>) obj;
            return Objects.equals(key, pair.getFirst()) && Objects.equals(value, pair.getSecond());
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = key.hashCode() * result;
            result = prime * result + value.hashCode();
            return result;
        }
    }
}
