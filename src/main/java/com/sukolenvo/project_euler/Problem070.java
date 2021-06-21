package com.sukolenvo.project_euler;

import java.util.List;

public class Problem070 {

  int run() {
    int result = 0;
    double min = Integer.MAX_VALUE;
    for (int i = 2; i < 10_000_000; i++) {
      int phi = Problem072.getPhi(i);
      List<Integer> digitsNumber = Common.getDigitsList(i);
      List<Integer> digitsPhi = Common.getDigitsList(phi);
      if (digitsPhi.size() == digitsNumber.size()) {
        digitsNumber.sort(Integer::compareTo);
        digitsPhi.sort(Integer::compareTo);
        if (digitsPhi.equals(digitsNumber)) {
          if (min > (double) i / phi) {
            min = (double) i / phi;
            result = i;
          }
        }
      }
    }
    return result;
  }
}
