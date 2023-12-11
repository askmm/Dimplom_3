package ru.stellarburger.testui;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

    public static String randomPassword() {
        return RandomStringUtils.randomAlphanumeric(6, 10);
    }

    public static String randomEmail() {
        return RandomStringUtils.randomAlphanumeric(5, 10)
                + "@" + RandomStringUtils.randomAlphanumeric(5, 10) + ".com";
    }

    public static String randomName() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }
}
