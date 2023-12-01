package com.adventofcode.day_01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo {

  public static void main(String[] args) {
    String texto = "threetwoooasdtwone2";  // Con palabra solapada: twone

    Pattern patron = Pattern.compile("(?=(one|two|three))");

    Matcher matcher = patron.matcher(texto);
    while (matcher.find()) {
      System.out.println(matcher.group(1));
    }
  }

}