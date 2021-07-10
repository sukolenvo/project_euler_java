package com.sukolenvo.project_euler;

import java.util.HashMap;
import java.util.Map;

public class Problem114 {

  private Map<Integer, Long> cache = new HashMap<>();

  long run(int area) {
    return paint(area);
  }

  private long paint(int area) {
    if (cache.containsKey(area)) {
      return cache.get(area);
    }
    long result = 1;
    for (int i = 3; i <= area; i++) {
      for (int j = 0; j <= area - i; j++) {
        result += paint(area - j - i - 1);
      }
    }
    cache.put(area, result);
    return result;
  }
}
