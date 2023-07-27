package org.ashe.kappa.infra;

import org.springframework.util.Assert;

public class RegexUtil {

    private RegexUtil() {
    }

    private static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static void assertEmail(String email) {
        Assert.isTrue(email.matches(EMAIL), String.format("%s is invalid email", email));
    }

}
