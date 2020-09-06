package com.sukolenvo.project_euler;

public class Problem028 {

  int run(int size) {
    int result = 0;
    int current = size * size;
    int step = size - 1;
    while (current > 1) {
      for (int i = 0; i < 4; i++) {
        result += current;
        current -= step;
      }
      step -= 2;
    }
    return result + 1;
  }
}
