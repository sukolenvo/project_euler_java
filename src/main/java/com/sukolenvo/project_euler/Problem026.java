package com.sukolenvo.project_euler;

import java.util.HashSet;
import java.util.Set;

public class Problem026 {

  int run() {
    int max = 0;
    int d = 1;
    for (int i = 2; i < 1_000; i++) {
      int period = getPeriod(1, i);
      if (period > max) {
        max = period;
        d = i;
      }
    }
    return d;
  }

  int getPeriod(int number, int denominator) {
    Set<Integer> mods = new HashSet<>();
    while (true) {
      while (number < denominator) {
        number *= 10;
      }
      number %= denominator;
      if (number == 0) {
        return 0;
      }
      if (!mods.add(number)) {
        return mods.size();
      }
    }
  }
}
