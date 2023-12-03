package com.adventofcode.day_02;

import com.adventofcode.utils.LineReader;

import java.util.*;

public class Part1 {

  private final Map<String, Integer> MAX_VALID_SCORES = new HashMap<>(){
    {
      put("red", 12);
      put("green", 13);
      put("blue", 14);
    }
  };

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
    if(checkValid(scores)){
      return Integer.parseInt(tokens.get(1));
    }
    return null;
  }

  public boolean checkValid(Map<String, Integer> scores) {
    for (String key : scores.keySet()) {
      if(scores.get(key) > MAX_VALID_SCORES.get(key)){
        return false;
      }
    }
    return true;
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

