package com.techlab.business.utilities;

public class StringUtil {
    public static String toTitleCase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
