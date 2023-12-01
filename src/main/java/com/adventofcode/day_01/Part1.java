package com.adventofcode.day_01;

import com.adventofcode.utils.LineReader;

public class Part1 {
  public Integer solve(String input){
    return new LineReader(input)
      .getLines()
      .map(this::processLine)
      .map(Integer::parseInt)
      .reduce(0, Integer::sum);
  }

  private String processLine(String line){
    char[] charArray = line.toCharArray();
    return String.valueOf(getFirstNumber(charArray)) + getLastNumber(charArray);
  }

  private Character getFirstNumber(char[] charArray){
    for (char c : charArray) {
      if (Character.isDigit(c)) {
        return c;
      }
    }
    return '0';
  }

  private Character getLastNumber(char[] charArray){
    for (int i = charArray.length-1; i >= 0; i--) {
      if (Character.isDigit(charArray[i])) {
        return charArray[i];
      }
    }
    return '0';
  }


}
