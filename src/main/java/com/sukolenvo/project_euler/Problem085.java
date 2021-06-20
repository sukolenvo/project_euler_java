package com.sukolenvo.project_euler;

public class Problem085 {

  int run() {
    int result = 0;
    int min = 2_000_000;
    for (int i = 1; i <= 2_001; i++) {
      for (int j = i; j <= 2_001; j++) {
        int current = i * (i + 1) * j * (j + 1) / 4;
        int rectangles = Math.abs(2_000_000 - current);
        if (rectangles < min) {
          result = i * j;
          min = rectangles;
        }
      }
    }
    return result;
  }
}
