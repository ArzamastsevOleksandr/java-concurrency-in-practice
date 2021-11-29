package util;

public class NumberUtil {

    public static boolean isPrime(long current) {
        if (current <= 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(current); ++i) {
            if (current % i == 0) {
                return false;
            }
        }
        return true;
    }

}
