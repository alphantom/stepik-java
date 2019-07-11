package Streams;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void parse1() throws Exception {
        String input = "Мама мыла-мыла-мыла раму!";
        String expected = "мыла\n" +
                "мама\n" +
                "раму\n";
        // compare an expected output to the actual output
        Assert.assertEquals(expected, doTest(input));
    }
    @Test
    public void parse2() throws Exception {
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel " +
                "ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt " +
                "efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. " +
                "Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.";
        String expected = "consectetur\n" +
                "faucibus\n" +
                "ipsum\n" +
                "lorem\n" +
                "adipiscing\n" +
                "amet\n" +
                "dolor\n" +
                "eget\n" +
                "elit\n" +
                "mi\n";
        // compare an expected output to the actual output
        Assert.assertEquals(expected, doTest(input));
    }
    @Test
    public void parse3() throws Exception {
        String input = "";
        String expected = "";
        // compare an expected output to the actual output
        Assert.assertEquals(expected, doTest(input));
    }

    public String doTest(String inputBytes) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputBytes.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PrintStream printStream = new PrintStream(outputStream);
        System.setIn(inputStream);
        System.setOut(printStream);
        Main.main(null);
        return new String(outputStream.toByteArray());
    }
}
