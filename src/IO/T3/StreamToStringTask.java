package IO.T3;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class StreamToStringTask {

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {

        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        StringBuilder sb = new StringBuilder();
        int a;
        while ((a = reader.read()) != -1) {
            sb.append((char)a);
        }
        reader.close();
        return sb.toString();
    }
}
