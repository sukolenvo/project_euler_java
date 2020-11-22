package com.sukolenvo.project_euler;

import java.util.HashMap;
import java.util.Map;

public class Problem092 {

  Map<Integer, Integer> cache = new HashMap<>();

  {
    cache.put(1, 1);
    cache.put(89, 89);
  }

  int run() {
    for (int i = 2; i < 10_000_000; i++) {
      runThroughChain(i);
    }
    return (int) cache.entrySet().stream()
        .filter(entry -> entry.getKey() < 10_000_000)
        .filter(entry -> entry.getValue() == 89)
        .count();
  }

  int runThroughChain(int start) {
    if (cache.containsKey(start)) {
      return cache.get(start);
    }
    int nextInChain = Common.getDigitsList(start).stream().mapToInt(i -> i * i).sum();
    int result = runThroughChain(nextInChain);
    cache.put(start, result);
    return result;
  }
}
