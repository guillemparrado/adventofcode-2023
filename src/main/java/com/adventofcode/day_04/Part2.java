package com.adventofcode.day_04;

import com.adventofcode.utils.LineReader;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Part2 {


  public Integer solve(String input) {
    List<Integer> matches = getMatches(input);
    return getInstancesCount(matches)
      .stream()
      .reduce(0, Integer::sum);
  }

  /**
   * @param input La dirección del fichero de entrada
   * @return El numero de resultados ganadores de cada línea por posición (resultados de la línea 0 en la posición 0, etc.)
   */
  private List<Integer> getMatches(String input) {
    return new LineReader(input)
      .getLines()
      .map(this::getLineMatches)
      .toList();
  }


  private Integer getLineMatches(String line) {
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
    return count;
  }


  private List<String> tokenizeSpace(String line) {
    List<String> tokens = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(line, " ");
    while (tokenizer.hasMoreTokens()) {
      tokens.add(tokenizer.nextToken());
    }
    return tokens;
  }


  /**
   * @param matches El numero de resultados ganadores de cada línea por posición
   * @return El numero de instancias finales de cada línea
   */
  private @NotNull List<Integer> getInstancesCount(List<Integer> matches) {
    List<Integer> instancesCount = new ArrayList<>(Collections.nCopies(matches.size(), 0));

    Queue<Integer> pendingLineCalculations = new LinkedList<>();
    for(int i = 0; i < matches.size(); i++){
      pendingLineCalculations.add(i);
    }

    while(!pendingLineCalculations.isEmpty()){
      int idx = pendingLineCalculations.remove();
      instancesCount.set(idx, instancesCount.get(idx) + 1);
      for(int i =idx + 1; i <= idx + matches.get(idx) && i < matches.size(); i++){
        pendingLineCalculations.add(i);
      }
    }

    return instancesCount;
  }
}
