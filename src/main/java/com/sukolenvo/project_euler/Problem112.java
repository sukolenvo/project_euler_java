package com.sukolenvo.project_euler;

import java.util.List;

public class Problem112 {

  int run() {
    int bouncyNumbers = 21780 / 10 * 9;
    int nonBouncy = 21780 / 10;
    for (int i = 21781; ; i++) {
      List<Integer> digits = Common.getDigitsList(i);
      boolean increasing = true;
      boolean decreasing = true;
      for (int j = 1; j < digits.size(); j++) {
        increasing &= digits.get(j - 1) <= digits.get(j);
        decreasing &= digits.get(j - 1) >= digits.get(j);
        if (!increasing && !decreasing) {
          break;
        }
      }
      if (!increasing && !decreasing) {
        bouncyNumbers++;
        if (bouncyNumbers / nonBouncy >= 99) {
          return i;
        }
      } else {
        nonBouncy++;
      }
    }
  }
}
