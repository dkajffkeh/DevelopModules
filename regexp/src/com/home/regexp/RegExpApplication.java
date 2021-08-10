package com.home.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpApplication {

    public static void main(String[] args) {

        String pattern = "g+"; // RegularExpression 들어오는 자리.
        String val = "gragy12";

        boolean reg = Pattern.matches(pattern, val);
        System.out.println(reg);
    /*
    //




     */

    }
}
