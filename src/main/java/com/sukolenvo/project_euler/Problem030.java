package com.sukolenvo.project_euler;

public class Problem030 {

  int run() {
    int result = 0;
    for (int i = 10; i < Math.pow(9, 5) * 5; i++) {
      if (getDigitsFifthPow(i) == i) {
        result += i;
      }
    }
    return result;
  }

  private int getDigitsFifthPow(int number) {
    int sum = 0;
    while (number > 0) {
      sum += Math.pow(number % 10, 5);
      number /= 10;
    }
    return sum;
  }
}
