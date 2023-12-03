package com.adventofcode.day_03;

import com.adventofcode.utils.LineReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

  Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
  private List<String> lines;
  public record Number(String value, int line, int column) {}


  public Integer solve(String input) {
    lines = new LineReader(input)
      .getLines()
      .toList();

    return getNumbers()
      .stream()
      .map(this::getNumberValue)
      //.peek(System.out::println)
      .reduce(0, Integer::sum);
  }

  private List<Number> getNumbers() {
    List<Number> numbers = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      numbers.addAll(getLineNumbers(i));
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

  private Integer getNumberValue(Number number) {
    return isPartNumber(number) ?
      Integer.parseInt(number.value()) :
      0;
  }

  private Boolean isPartNumber(Number number) {
    return !findAdjacentCharacters(number)
      .stream()
      .filter(character -> !Character.isLetterOrDigit(character) && !character.equals('.'))
      .toList()
      .isEmpty();
  }

  private List<Character> findAdjacentCharacters(Number number) {
    List<Character> characters = new ArrayList<>();
    String line = lines.get(number.line);

    int colStart = number.column != 0 ?
      number.column - 1 :  // Si tiene columna anterior
      0;

    int colEnd = number.column + number.value.length() - 1;
    colEnd = colEnd < line.length() - 1 ?
      colEnd + 1:  // Si tiene columna posterior
      colEnd;

    // Línea del número
    characters.add(line.charAt(colStart));
    characters.add(line.charAt(colEnd));

    // Línea anterior
    if(number.line > 0){
      line = lines.get(number.line - 1);
      for(int i = colStart; i <= colEnd; i++){
        characters.add(
          line.charAt(i)
        );
      }
    }

    // Línea posterior
    if(number.line < lines.size() - 1){
      line = lines.get(number.line + 1);
      for(int i = colStart; i <= colEnd; i++){
        characters.add(
          line.charAt(i)
        );
      }
    }

    return characters;
  }

}
