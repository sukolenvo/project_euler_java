package com.sukolenvo.project_euler;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class Problem014 {

  private Map<Long, Integer> cache = new ConcurrentHashMap<>();

  int run() {
    IntStream.range(1, 1_000_000)
        .parallel()
        .forEach(this::calc);
    return cache.entrySet().stream()
        .filter(entry -> entry.getKey() < 1_000_000)
        .max(Comparator.comparingInt(Entry::getValue))
        .map(Entry::getKey)
        .map(Long::intValue)
        .orElseThrow();
  }

  private int calc(long number) {
    if (number == 1) {
      return 1;
    }
    if (cache.containsKey(number)) {
      return cache.get(number);
    }
    if (number % 2 == 0) {
      int result = calc(number / 2) + 1;
      cache.put(number, result);
      return result;
    }
    int result = calc(number * 3 + 1) + 1;
    cache.put(number, result);
    return result;
  }

}
