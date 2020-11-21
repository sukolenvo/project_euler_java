package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem097 {

  long run() {
    String value = BigInteger.valueOf(2).pow(7830457).multiply(BigInteger.valueOf(28433)).toString();
    return Long.parseLong(value.substring(value.length() - 10)) + 1;
  }
}
