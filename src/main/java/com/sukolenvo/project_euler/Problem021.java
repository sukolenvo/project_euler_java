package com.sukolenvo.project_euler;

public class Problem021 {

  int run() {
    int result = 0;
    for (int i = 2; i < 10_000; i++) {
      int sum = divisorsSum(i);
      if (sum > i) {
        if (divisorsSum(sum) == i) {
          result += i;
          result += sum;
        }
      }
    }
    return result;
  }
  
  private int divisorsSum(int number) {
    int result = 1;
    for (int i = 2; i <= Math.sqrt(number); i++) {
      if (number % i == 0) {
        result += number / i == number ? i : i + number / i;
      }
    }
    return result;
  }
}
