package Streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    /**
     * Напишите программу, читающую из System.in текст в кодировке UTF-8, подсчитывающую
     * в нем частоту появления слов, и в конце выводящую 10 наиболее часто встречающихся слов.
     *
     * Словом будем считать любую непрерывную последовательность символов,
     * состоящую только из букв и цифр. Например, в строке "Мама мыла раму 33 раза!"
     * ровно пять слов: "Мама", "мыла", "раму", "33" и "раза".
     *
     * Подсчет слов должен выполняться без учета регистра, т.е. "МАМА", "мама" и "Мама" —
     * это одно и то же слово. Выводите слова в нижнем регистре.
     *
     * Если в тексте меньше 10 уникальных слов, то выводите сколько есть.
     *
     * Если в тексте некоторые слова имеют одинаковую частоту, т.е. их нельзя однозначно
     * упорядочить только по частоте, то дополнительно упорядочите слова с одинаковой частотой
     * в лексикографическом порядке.
     * @param args
     */
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            reader.lines().map(line -> line.split("[^а-яА-Я0-9a-zA-Z]"))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .filter(x -> !x.getKey().equals(""))
                    .limit(10)
                    .map(Map.Entry::getKey)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
