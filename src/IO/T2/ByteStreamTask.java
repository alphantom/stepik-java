package IO.T2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteStreamTask {

    public static void main(String... args) {

        try(InputStream inputStream = new ByteArrayInputStream(new byte[] {0x33, 0x45, 0x01})) {
            System.out.println(checkSumOfStream(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Контрольная сумма представляет собой число типа int
     * Контрольная сумма пустого набора данных равна нулю.
     * Контрольная сумма непустого набора данных вычисляется по следующей рекуррентной формуле:
     *  Cn+1=rotateLeft(Cn) xor bn+1
     *      Cn — контрольная сумма первых n байт данных,
     *      rotateLeft — циклический сдвиг бит числа на один бит влево,
     *      bn — n-ный байт данных.
     * @param inputStream набор данных
     * @return контрольная сумма
     * @throws IOException
     */
    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int checkSum = 0;
        int nextByte;
        while ((nextByte = inputStream.read()) != -1) {
            checkSum = Integer.rotateLeft(checkSum, 1)^nextByte;
        }
        return checkSum;
    }
}
