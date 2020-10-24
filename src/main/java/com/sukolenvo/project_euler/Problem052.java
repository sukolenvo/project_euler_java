package com.sukolenvo.project_euler;

import java.util.Collections;
import java.util.List;

public class Problem052 {

  long run() {
    for (long i = 1; ; i++) {
      if (Common.getDigitCount(i) == Common.getDigitCount(i * 6)) {
        List<Integer> digits = Common.getDigitsList(i);
        List<Integer> digits2 = Common.getDigitsList(i * 2);
        if (!digits.containsAll(digits2) || !digits2.containsAll(digits)) {
          continue;
        }
        List<Integer> digits3 = Common.getDigitsList(i * 3);
        if (!digits.containsAll(digits3) || !digits3.containsAll(digits)) {
          continue;
        }
        List<Integer> digits4 = Common.getDigitsList(i * 4);
        if (!digits.containsAll(digits4) || !digits4.containsAll(digits)) {
          continue;
        }
        List<Integer> digits5 = Common.getDigitsList(i * 5);
        if (!digits.containsAll(digits5) || !digits5.containsAll(digits)) {
          continue;
        }
        List<Integer> digits6 = Common.getDigitsList(i * 6);
        if (!digits.containsAll(digits6) || !digits6.containsAll(digits)) {
          continue;
        }
        Collections.sort(digits);
        Collections.sort(digits2);
        Collections.sort(digits3);
        Collections.sort(digits4);
        Collections.sort(digits5);
        Collections.sort(digits6);
        if (digits.equals(digits2) && digits.equals(digits3)
        && digits.equals(digits4) && digits.equals(digits5) && digits.equals(digits6)) {
          return i;
        }
      }
    }
  }
}
