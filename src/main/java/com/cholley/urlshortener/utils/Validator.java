package com.cholley.urlshortener.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Validator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * verify if the url in param is valid
     * @param url to validate
     * @return boolean
     */
    public static boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    /**
     * verify if the shortened url in param is valid (base 62 and length min 1 and max 10)
     * @param shortenedUrl to validate
     * @return boolean
     */
    public static boolean isValidShortenedUrl(final String shortenedUrl) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile("[A-Za-z0-9]{1,10}");
        // Match regex against shortenedUrl
        final Matcher matcher = pattern.matcher(shortenedUrl);
        return matcher.matches();
    }

}
