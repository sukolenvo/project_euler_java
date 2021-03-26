package com.sukolenvo.project_euler;

import java.util.Comparator;
import java.util.stream.IntStream;

public class Problem076 {

  int run() {
    return Problem031.countWays(IntStream.rangeClosed(1, 100).boxed()
        .sorted(Comparator.reverseOrder())
        .mapToInt(Integer::intValue)
        .toArray(), 100) - 1;
  }
}
