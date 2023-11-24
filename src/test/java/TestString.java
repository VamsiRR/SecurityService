import org.springframework.security.crypto.codec.Utf8;

import java.util.Arrays;
import java.util.UUID;

public class TestString {

    public static void main(String[] args) {

        String a = "prasanna@yahoomail.com";

        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            count += a.charAt(i);
        }

        System.out.println(count);
    }
}
