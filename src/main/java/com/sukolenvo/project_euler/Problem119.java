package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem119 {

  long run(int index) {
    Set<BigInteger> result = new HashSet<>();
    BigInteger maxKnown = null;
    for (int i = 2; i < 100; i++) {
      for (int j = 1; j < 500; j++) {
        BigInteger n = BigInteger.valueOf(i).pow(j);
        if (n.compareTo(BigInteger.TEN) < 0) {
          continue;
        }
        if (maxKnown != null && n.compareTo(maxKnown) > 0) {
          break;
        }
        if (n.toString().chars()
            .map(c -> c - '0')
            .sum() == i) {
          if (result.size() < index) {
            result.add(n);
          } else {
            result.remove(maxKnown);
            result.add(n);
            maxKnown = result.stream().max(BigInteger::compareTo).orElseThrow();
          }
        }
      }
    }
    return result.stream()
        .sorted()
        .skip(index - 1)
        .findFirst()
        .orElseThrow()
        .longValue();
  }
}
