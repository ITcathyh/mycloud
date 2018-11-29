package hyh.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 加密工具类
 *
 * @author ITcathyh
 * @version 1.0
 */
public final class EncryptUtil {
    private static final Object SALT = new Md5Hash("ITcathyh");
    private static final String HASH_ALGORITHM_NAME = "MD5";
    private static final short ITERATIONS = 1024;

    public static String getMD5(String str) {
        Object result = new SimpleHash(HASH_ALGORITHM_NAME, str, SALT, ITERATIONS);

        return result.toString();
    }
}
