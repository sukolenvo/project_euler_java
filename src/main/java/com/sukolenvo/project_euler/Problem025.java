package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem025 {

  long run() {
    BigInteger prev = BigInteger.ONE;
    BigInteger current = BigInteger.ONE;
    for (long i = 3; ; i++) {
      BigInteger next = current.add(prev);
      if (next.toString().length() >= 1000) {
        return i;
      }
      prev = current;
      current = next;
    }
  }
}
