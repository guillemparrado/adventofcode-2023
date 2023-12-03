package com.adventofcode.day_03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
    public static void main(String[] args) {
      String texto = "467..114..";

      // Patrón de regex para encontrar números
      Pattern patron = Pattern.compile("\\d+");

      // Crear un objeto Matcher
      Matcher matcher = patron.matcher(texto);

      // Buscar coincidencias en el texto
      while (matcher.find()) {
        String numero = matcher.group();
        int posicion = matcher.start();
        System.out.println("Número: " + numero + ", Posición: " + posicion);
      }
    }

}
