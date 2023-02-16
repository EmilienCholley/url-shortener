package com.cholley.urlshortener.utils;

public class Base62Encoder {

    static final String BASE62CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Base62Encoder() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * convert base 10 param in base 62
     * @param base10 the value to convert
     * @return base 62 value result
     */
    public static String base10ToBase62(long base10) {
        StringBuilder sb = new StringBuilder();
        while (base10 != 0) {
            sb.append(BASE62CHARS.charAt((int)(base10 % 62)));
            base10 /= 62;
        }
        while (sb.length() < 6) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }

}
