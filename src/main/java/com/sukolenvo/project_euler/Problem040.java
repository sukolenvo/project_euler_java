package com.sukolenvo.project_euler;

public class Problem040 {

  int run() {
    int result = 1;
    int digits = 0;
    int lookingFor = 1;
    for (int i = 1; lookingFor <= 1_000_000; i++) {
      int digitCount = Common.getDigitCount(i);
      digits += digitCount;
      if (digits >= lookingFor) {
        int index = digits - lookingFor;
        result *= Common.getDigitsList(i).get(index);
        lookingFor *= 10;
      }
    }
    return result;
  }
}
