package com.sukolenvo.project_euler;

public class Problem071 {

  int run() {
    int maxNumerator = 0;
    double max = 0;
    for (int i = 10; i < 1_000_000; i++) {
      int numerator = i % 7 == 0 ? i / 7 * 3 - 1 : (int) Math.floor((double) i * 3 / 7);
      if ((double) numerator / i > max) {
        max = (double) numerator / i;
        maxNumerator = numerator;
      }
    }
    return maxNumerator;
  }
}
