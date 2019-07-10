package Collections;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MainTest {
    @Test
    public void parse1() throws Exception {
        String input = "1 2 3 4 5 6";
        String expected = "6 4 2";
        // compare an expected output to the actual output
        Assert.assertEquals(expected, doTest(input));
    }
    @Test
    public void parse2() throws Exception {
        String input = "-1 -2 -3 -4 -5 -6";
        String expected = "-6 -4 -2";
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

