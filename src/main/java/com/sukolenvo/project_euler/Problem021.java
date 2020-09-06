package com.sukolenvo.project_euler;

public class Problem021 {

  int run() {
    int result = 0;
    for (int i = 2; i < 10_000; i++) {
      int sum = Common.divisorsSum(i);
      if (sum > i) {
        if (Common.divisorsSum(sum) == i) {
          result += i;
          result += sum;
        }
      }
    }
    return result;
  }
}
