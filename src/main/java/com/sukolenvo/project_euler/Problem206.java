package com.sukolenvo.project_euler;

import java.util.List;

public class Problem206 {

  int run() {
    for (int i = (int) Math.sqrt(10203040506070809.); ; i ++) {
      if (i % 10 == 3 || i % 10 == 7) {
        long test = (long) i * i;
        List<Integer> digits = Common.getDigitsList(test);
        if (digits.size() > 17) {
          throw new IllegalStateException("Match not found");
        }
        if (matched(digits)) {
          return i * 10;
        }
      }
    }
  }

  boolean matched(List<Integer> digits) {
    for (int j = 0; j < digits.size(); j += 2) {
      if (digits.get(j) != 9 - j / 2) {
        return false;
      }
    }
    return true;
  }
}
