package com.adventofcode.day_04;

import com.adventofcode.utils.LineReader;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Part1 {


  public Long solve(String input) {
    return new LineReader(input)
      .getLines()
      .map(this::processLine)
      //.peek(System.out::println)
      .reduce(0L, Long::sum);
  }

  private @NotNull Long processLine(String line) {
    StringTokenizer groupTokenizer = new StringTokenizer(line, ":|");

    groupTokenizer.nextToken();
    List<String> winning = tokenizeSpace(groupTokenizer.nextToken());
    List<String> result = tokenizeSpace(groupTokenizer.nextToken());

    int count = 0;
    for(String elem : winning){
      if(result.contains(elem)){
        count++;
      }
    }

    return count > 0 ?
      Math.round(Math.pow(2, count - 1)) :
      0;
  }

  private List<String> tokenizeSpace(String line) {
    List<String> tokens = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(line, " ");
    while (tokenizer.hasMoreTokens()) {
      tokens.add(tokenizer.nextToken());
    }
    //System.out.println(tokens);
    return tokens;
  }

}
