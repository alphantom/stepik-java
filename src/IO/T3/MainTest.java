package IO.T3;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MainTest {
    @Test
    public void parse1() throws Exception {
        String input = "1 2 3";
        String expected = "6,000000";

        // compare an expected output to the actual output
        Assert.assertEquals(expected, doTest(input));
    }

    @Test
    public void parse2() throws Exception {
        String input = "a1 b2 c3";
        String expected = "0,000000";

        // compare an expected output to the actual output
        Assert.assertEquals(expected, doTest(input));
    }

    @Test
    public void parse3() throws Exception {
        String input = "-1e3\n" +
                "18 .111 11bbb";
        String expected = "-981,889000";

        // compare an expected output to the actual output
        Assert.assertEquals(expected, doTest(input));
    }

    public String doTest(String inputBytes) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputBytes.getBytes(Main.CHARSET));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PrintStream printStream = new PrintStream(outputStream);
        System.setIn(inputStream);
        System.setOut(printStream);
        Main.main(null);
        return outputStream.toString(Main.CHARSET);
    }

}
