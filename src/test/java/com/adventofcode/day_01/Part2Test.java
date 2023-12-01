package com.adventofcode.day_01;

import static org.junit.jupiter.api.Assertions.*;

class Part2Test {

  @org.junit.jupiter.api.Test
  void solve() {
    new Part2().solve2("src/test/java/com/adventofcode/day_01/test_input.txt");
    assertEquals(281, new Part2().solve("src/test/java/com/adventofcode/day_01/test_input.txt"));
  }
}