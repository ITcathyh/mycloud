package hyh.util;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Random;

public final class GetRandomString {
    private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static Random random = new Random(new Date().getTime());

    public static String getRandomString(int len) {
        StringBuilder stra = new StringBuilder("");

        for (int i = 0; i < len; i++) {
            stra.append(CHARS[random.nextInt(CHARS.length)]);
        }

        return stra.toString();
    }

    public static String getRandomStringWithTimeInfo(int len) {
        if (len < 25) {
            return null;
        }

        StringBuilder stra = new StringBuilder("");
        DateTime dateTime = new DateTime();

        stra.append(dateTime.toString("yyyyMMddHHmmss"));
        stra.append(getRandomString(8));
        stra.append(dateTime.toString("sss"));
        stra.append(getRandomString(len - 25));

        return stra.toString();
    }

    public static String getRandomStringWithTimeInfo(int len, String key) {
        if (len - key.length() < 25) {
            return null;
        }

        StringBuilder stra = new StringBuilder("");
        DateTime dateTime = new DateTime();

        stra.append(dateTime.toString("yyyyMMddHHmmss"));
        stra.append(getRandomString(8));
        stra.append(dateTime.toString("sss"));
        stra.append(key);
        stra.append(getRandomString(len - 25 - key.length()));

        return stra.toString();
    }

    public static void setRandom() {
        random = new Random(new Date().getTime());
    }
}
