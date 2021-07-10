package com.sukolenvo.project_euler;

import java.util.HashMap;
import java.util.Map;

public class Problem117 {

  private Map<Integer, Long> cache = new HashMap<>();

  long run(int area) {
    return paint(area);
  }

  long paint(int area) {
    if (cache.containsKey(area)) {
      return cache.get(area);
    }
    long result = 1;
    for (int i = 0; i <= area - 2; i++) {
      result += paint(area - i - 2);
    }
    for (int i = 0; i <= area - 3; i++) {
      result += paint(area - i - 3);
    }
    for (int i = 0; i <= area - 4; i++) {
      result += paint(area - i - 4);
    }
    cache.put(area, result);
    return result;
  }
}
