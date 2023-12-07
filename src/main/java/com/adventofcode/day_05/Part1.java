package com.adventofcode.day_05;

import com.adventofcode.utils.LineReader;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Part1 {


  public Long solve(String input) {

    List<String> lines = new LineReader(input).getLines().toList();
    List<Long> seeds = this.getSeeds(lines.get(0));
    List<List<List<Long>>> maps = this.getMaps(lines.subList(2, lines.size()));

    return seeds
      .stream()
      .map(seed -> getSeedLocation(seed, maps))
      .reduce(Long.MAX_VALUE, Long::min);

  }

  private List<Long> getSeeds(String line) {
    List<String> tokens = tokenize(line);
    return tokens
      .subList(1, tokens.size())
      .stream()
      //.peek(System.out::println)
      .map(Long::parseLong)
      .toList();
  }

  private @NotNull List<List<List<Long>>> getMaps(List<String> lines) {

    List<List<List<Long>>> maps = new ArrayList<>();
    int i = 0;
    maps.add(new ArrayList<>());

    for (String line : lines) {
      if (line.isEmpty()) {
        maps.add(new ArrayList<>());
        i++;
      } else {
        if(!Character.isDigit(line.charAt(0))) {
          continue;
        }
        maps.get(i).add(parseMapLine(line));
      }
    }

    return maps;
  }

  private @NotNull Long getSeedLocation(Long seed, List<List<List<Long>>> maps) {
    Long result = seed;
    for(List<List<Long>> map : maps) {
      result = applyMap(result, map);
    }
    //System.out.println(seed + " " + result);
    return result;
  }


  private @NotNull Long applyMap(long seed, List<List<Long>> map) {
    long result = seed;
    for(List<Long> line : map) {
      long dst = line.get(0);
      long src = line.get(1);
      long range = line.get(2);
      if(seed >= src && seed < src + range) {
        result = seed - src + dst;
      }
    }
    return result;
  }


  private List<Long> parseMapLine(String line) {
    return tokenize(line)
      .stream()
      .map(Long::parseLong)
      .toList();
  }

  private List<String> tokenize(String line) {
    List<String> tokens = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(line, ": ");
    while (tokenizer.hasMoreTokens()) {
      tokens.add(tokenizer.nextToken());
    }
    return tokens;
  }

}
