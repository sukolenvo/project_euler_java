package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Problem108 {

  BigInteger run(int solutions) {
    return IntStream.iterate(2, i -> i + 1)
        .parallel()
        .filter(i -> countFractions(i) > solutions)
        .mapToObj(BigInteger::valueOf)
        .findFirst()
        .orElseThrow();
  }

  private int countFractions(int n) {
    int count = 2;
    for (int i = n + 2; i < 2 * n; i++) {
      if ((long) i * n % (n - i) == 0) {
        count++;
      }
    }
    return count;
  }
}
