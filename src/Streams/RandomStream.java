package Streams;

import java.util.stream.IntStream;

public class RandomStream {

    public static void main(String[] args) {

    }

    /**
     * Берется какое-то начальное неотрицательное число
     * (оно будет передаваться в ваш метод проверяющей системой).
     * Первый элемент последовательности устанавливается равным этому числу.
     * Следующие элементы вычисляются по рекуррентной формуле Rn+1=mid(Rn^2),
     * где mid — это функция, выделяющая второй, третий и четвертый разряд переданного числа.
     * Например, mid(123456)=345.
     * @param seed
     * @return
     */
    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.iterate(seed, x -> (x*x / 10 % 1000));
    }
}
