package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem016 {

  int run(int power) {
    int sum = 0;
    for (char digit : BigInteger.TWO.pow(power).toString().toCharArray()) {
      sum += digit - '0';
    }
    return sum;
  }
}
