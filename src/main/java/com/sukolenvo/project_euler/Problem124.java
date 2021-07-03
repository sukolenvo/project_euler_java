package com.sukolenvo.project_euler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Problem124 {

  int run() {
    Map<Integer, Long> rads = new HashMap<>();
    for (int i = 1; i <= 100_000; i++) {
      long rad = Common.getPrimeFactors(i).stream()
          .distinct()
          .mapToLong(Long::longValue)
          .reduce(1, (a, b) -> a * b);
      rads.put(i, rad);
    }
    return rads.entrySet()
        .stream()
        .sorted(Entry.<Integer, Long>comparingByValue().thenComparing(Entry::getValue))
        .skip(9999)
        .findFirst()
        .orElseThrow()
        .getKey();
  }
}
