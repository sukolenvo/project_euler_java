package com.sukolenvo.project_euler;

public class Problem012 {

  long run() {
    long number = 1;
    for (long i = 2; ; i++) {
      number += i;
      if (number < 0) {
        throw new IllegalStateException("Overflow");
      }
      if (Common.getFactorsCount(number) > 500) {
        return number;
      }
    }
  }
}
