package com.sukolenvo.project_euler;

public class Problem179 {

  int run() {
    int result = 0;
    int expectedDivisors = 1;
    for (int i = 2; i < 10_000_000; i++) {
      int factorsCount = Common.getFactorsCount(i);
      if (factorsCount == expectedDivisors) {
        result++;
      }
      expectedDivisors = factorsCount;
    }
    return result;
  }
}
