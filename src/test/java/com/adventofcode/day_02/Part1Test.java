package com.adventofcode.day_02;

import static org.junit.jupiter.api.Assertions.*;

class Part1Test {

  @org.junit.jupiter.api.Test
  void solve() {
    new Part1().solve("src/test/java/com/adventofcode/day_2/test_input.txt");
    assertEquals(8, new Part1().solve("src/test/java/com/adventofcode/day_2/test_input.txt"));
  }
}