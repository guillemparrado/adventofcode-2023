package com.adventofcode.day_01;

import com.adventofcode.utils.LineReader;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part2 {

  private final Map<String, String> numbers;
  private final Pattern pattern;

 public Part2() {
    numbers = new HashMap<>();
    numbers.put("one", "1");
    numbers.put("two", "2");
    numbers.put("three", "3");
    numbers.put("four", "4");
    numbers.put("five", "5");
    numbers.put("six", "6");
    numbers.put("seven", "7");
    numbers.put("eight", "8");
    numbers.put("nine", "9");

   String regex = "(?=(" + String.join("|", numbers.keySet()) + "|[0-9]))";
   pattern = Pattern.compile(regex);
 }

  public Integer solve(String input){
    return new LineReader(input)
      .getLines()
      .map(this::processLine)
      .map(Integer::parseInt)
      .reduce(0, Integer::sum);
  }

  public void solve2(String input){
    new LineReader(input)
      .getLines()
      .map(this::processLine)
      .forEach(System.out::println);
  }

  private String processLine(String line){

    Matcher matcher = pattern.matcher(line);
    String first = null;
    String last = null;

    if(matcher.find()){
      first = matcher.group(1);
      last = first;
    }
    while(matcher.find()){
      last = matcher.group(1);
    }

    return first != null && last != null ?
      toInteger(first) + toInteger(last) :
      "0";
  }

  public String toInteger(String number){
    return number.length() == 1 ? number : numbers.get(number);
  }


}
