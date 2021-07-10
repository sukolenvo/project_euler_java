package com.sukolenvo.project_euler;

import java.util.HashMap;
import java.util.Map;

public class Problem116 {

  Map<Integer, Map<Integer, Long>> cache = new HashMap<>();

  long run(int area) {
    return paint(area, 2) - 1 + paint(area, 3) - 1 + paint(area, 4) - 1;
  }

  long paint(int area, int blockSize) {
    if (area < blockSize) {
      return 1; // multiplier
    }
    if (area == blockSize) {
      return 2; // empty and full
    }
    cache.putIfAbsent(blockSize, new HashMap<>());
    if (cache.get(blockSize).containsKey(area)) {
      return cache.get(blockSize).get(area);
    }
    long result = 1; // empty
    for (int i = 0; i <= area - blockSize; i++) {
      result += paint(area - i - blockSize, blockSize);
    }
    cache.get(blockSize).put(area, result);
    return result;
  }
}
