package com.adventofcode.day_02;

import com.adventofcode.utils.LineReader;

import java.util.*;

public class Part2 {

  public Integer solve(String input) {

    return new LineReader(input)
      .getLines()
      .map(this::getLinePoints)
      .filter(Objects::nonNull)
      .reduce(0, Integer::sum);
  }


  public Integer getLinePoints(String line) {
    List<String> tokens = tokenize(line);
    Map<String, Integer> scores = getMaxScores(tokens);
    //System.out.println(scores);
    return getProduct(scores);
  }

  private Integer getProduct(Map<String, Integer> scores) {
    int product = 1;
    for (String key : scores.keySet()) {
      product *= scores.get(key);
    }
    return product;
  }


  public Map<String, Integer> getMaxScores(List<String> tokens) {
    Integer points = null;
    Map<String, Integer> scores = new HashMap<>();

    for (int i = 2; i < tokens.size(); i++) {
      if(points == null){
        points = Integer.parseInt(tokens.get(i));
      }
      else{
        if(scores.containsKey(tokens.get(i))){
          scores.put(tokens.get(i), Math.max(scores.get(tokens.get(i)), points));
        }
        else{
          scores.put(tokens.get(i), points);
        }
        points = null;
      }
    }
    return scores;
  }


  private List<String> tokenize(String line) {
    List<String> tokens = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(line, " ,;:");
    while (tokenizer.hasMoreTokens()) {
      tokens.add(tokenizer.nextToken());
    }
    return tokens;
  }
}
