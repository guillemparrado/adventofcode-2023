package com.adventofcode.day_02;

import static org.junit.jupiter.api.Assertions.*;

class Part2Test {

  @org.junit.jupiter.api.Test
  void solve() {
    new Part2().solve("src/test/java/com/adventofcode/day_2/test_input.txt");
    assertEquals(2286, new Part2().solve("src/test/java/com/adventofcode/day_2/test_input.txt"));
  }
}