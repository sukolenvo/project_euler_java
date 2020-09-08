package com.sukolenvo.project_euler;

public class Problem034 {

  long run() {
    long result = 0;
    int[] factorials = new int[10];
    for (int i = 0; i < factorials.length; i++) {
      factorials[i] = (int) Common.getFactorial(i);
    }
    for (int i = 10; i < Common.getFactorial(9) * 7; i++) {
      int sum = Common.getDigitsList(i).stream()
          .mapToInt(digit -> factorials[digit])
          .sum();
      if (sum == i) {
        result += i;
      }
    }
    return result;
  }
}
