package com.adventofcode.day_05;

import com.adventofcode.utils.LineReader;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Part2 {

  public record Range(long srcStart, long srcEnd, long length) {}
  public record Mapper(long srcStart, long srcEnd, long dstStart, long length) {
    public long toDst(long src) {
      return src - srcStart + dstStart;
    }
  }


  public Long solve(String input) {

    List<String> lines = new LineReader(input).getLines().toList();
    List<Range> ranges = this.getSeedRanges(lines.get(0));
    List<List<Mapper>> mapperLists = this.getMappers(lines.subList(1, lines.size()));

    for(List<Mapper> mapperList : mapperLists) {
      ranges = map(ranges, mapperList);
    }

    return ranges
      .stream()
      .map(range -> range.srcStart)
      .reduce(Long.MAX_VALUE, Long::min);
  }


  private List<Range> getSeedRanges(String line) {
    List<Range> seeds = new ArrayList<>();
    List<String> tokens = tokenize(line);
    for(int i = 1; i < tokens.size(); i+=2) {
      var start = Long.parseLong(tokens.get(i));
      var length = Long.parseLong(tokens.get(i + 1));
      seeds.add(
        new Range(
          start,
          start + length,
          length
        )
      );
    }
    return seeds;
  }


  private @NotNull List<List<Mapper>> getMappers(List<String> lines) {

    List<List<Mapper>> maps = new ArrayList<>();
    int mapIdx = -1;

    for (String line : lines) {
      if (line.isEmpty()) {
        maps.add(new ArrayList<>());
        mapIdx++;
      } else {
        if(!Character.isDigit(line.charAt(0))) {
          continue;
        }

        List<String> tokens = tokenize(line);
        var dstStart = Long.parseLong(tokens.get(0));
        var srcStart = Long.parseLong(tokens.get(1));
        var length = Long.parseLong(tokens.get(2));
        maps.get(mapIdx).add(new Mapper(
          srcStart,
          srcStart + length,
          dstStart,
          length
        ));
      }
    }

    return maps;
  }

  private List<Range> map(List<Range> rangeCollection, List<Mapper> mappersCollection) {
    List<Range> result = new ArrayList<>();
    var mappers = mappersCollection
      .stream()
      .sorted(Comparator.comparingLong(m -> m.srcStart))
      .toList();

    for(Range r : rangeCollection) {
      long rIndex = r.srcStart;
      int mIndex = 0;

      while(rIndex < r.srcEnd) {

        // Descartamos mappers que su src termina antes que empieze el rango
        while(mIndex < mappers.size() && mappers.get(mIndex).srcEnd <= rIndex) {
          mIndex++;
        }

        // Si no quedan mappers, devolvemos nuevo rango 1:1 hasta el final
        if(mIndex >= mappers.size()) {
          result.add(new Range(rIndex, r.srcEnd, r.srcEnd - rIndex));
          rIndex = r.srcEnd;
        }
        else {
          var m = mappers.get(mIndex);
          // Si range start no está dentro del mapper, devolvemos range 1:1 hasta el mapper src start
          if(rIndex < m.srcStart) {
            result.add(new Range(rIndex, m.srcStart, m.srcStart - rIndex));
            rIndex = m.srcStart;
          }

          // Si range start y end estan dentro del mapper
          else if(r.srcEnd <= m.srcEnd) {
            result.add(new Range(m.toDst(rIndex), m.toDst(r.srcEnd), r.srcEnd - rIndex));
            rIndex = r.srcEnd;
          }

          // si solo range start esta dentro del mapper
          else {
            var length = m.srcEnd - rIndex;
            result.add(new Range(
              m.toDst(rIndex),
              m.toDst(m.srcEnd),
              length)
            );
            rIndex += length;
          }
        }

      }
    }

    return result;
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
