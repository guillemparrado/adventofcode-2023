package com.adventofcode.day_03;

import com.adventofcode.utils.LineReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

  Pattern ASTERISK_PATTERN = Pattern.compile("\\*");
  Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

  private List<String> lines;
  public record Asterisk(int line, int column) {}
  public record Number(String value, int line, int column) {}



  public Integer solve(String input) {
    lines = new LineReader(input)
      .getLines()
      .toList();

    return getAsterisks()
      .stream()
      .map(this::getAsteriskValue)
      //.peek(System.out::println)
      .reduce(0, Integer::sum);
  }

  private List<Asterisk> getAsterisks() {
    List<Asterisk> asterisks = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      asterisks.addAll(getLineAsterisks(i));
    }
    return asterisks;
  }

  private List<Asterisk> getLineAsterisks(int i) {
    Matcher matcher = ASTERISK_PATTERN.matcher(lines.get(i));
    List<Asterisk> asterisks = new ArrayList<>();
    while (matcher.find()) {
      asterisks.add(new Asterisk(
        i,
        matcher.start()
      ));
    }
    //System.out.println(asterisks);
    return asterisks;
  }

  private Integer getAsteriskValue(Asterisk asterisk) {
    var numbers = findAdjacentNumbers(asterisk);
    return numbers.size() == 2 ?
      numbers.get(0) * numbers.get(1) :
      0;
  }


  private List<Integer> findAdjacentNumbers(Asterisk asterisk) {
    List<Integer> numbers = new ArrayList<>();
    String line = lines.get(asterisk.line);

    // Izquierda
    if(asterisk.column > 0 && Character.isDigit(line.charAt(asterisk.column - 1))){
      int start = asterisk.column;
      for(int i = asterisk.column - 1; i >= 0; i--) {
        if(Character.isDigit(line.charAt(i))){
          start = i;
        } else {
          break;
        }
      }
      numbers.add(Integer.parseInt(line.substring(start, asterisk.column)));
    }

    // Derecha
    if(asterisk.column < line.length() - 1 && Character.isDigit(line.charAt(asterisk.column + 1))){
      int end = asterisk.column;
      for(int i = asterisk.column + 1; i < line.length(); i++) {
        if(Character.isDigit(line.charAt(i))){
          end = i;
        } else {
          break;
        }
      }
      numbers.add(Integer.parseInt(line.substring(asterisk.column+1, end + 1)));
    }

    // Arriba
    if(asterisk.line > 0){
      numbers.addAll(
        getTouchingNumbers(
          asterisk.column, asterisk.line - 1
        ));
    }

    // Abajo
    if(asterisk.line < lines.size() - 1){
      numbers.addAll(
        getTouchingNumbers(
          asterisk.column, asterisk.line + 1
        ));
    }


    return numbers;
  }

  private List<Integer> getTouchingNumbers(int asteriskColumn, int line) {
    List<Integer> numbers = new ArrayList<>();
    var lineNumbers = getLineNumbers(line);
    for(Number number : lineNumbers){
      if(
        number.column <= asteriskColumn + 1 &&
          number.column + number.value.length() >= asteriskColumn
      ) {
        numbers.add(Integer.parseInt(number.value));
      }
    }
    return numbers;
  }

  private List<Number> getLineNumbers(int i) {
    Matcher matcher = NUMBER_PATTERN.matcher(lines.get(i));
    List<Number> numbers = new ArrayList<>();
    while (matcher.find()) {
      numbers.add(new Number(
        matcher.group(),
        i,
        matcher.start()
      ));
    }
    //System.out.println(numbers);
    return numbers;
  }



}
