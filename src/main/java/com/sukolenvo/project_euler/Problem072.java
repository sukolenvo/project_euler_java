package com.sukolenvo.project_euler;

import java.util.List;

public class Problem072 {

  /**
   * https://en.wikipedia.org/wiki/Euler%27s_totient_function
   */
  long run() {
    long result = 0;
    for (int i = 2; i <= 1_000_000; i++) {
      result += getPhi(i);
    }
    return result;
  }

  int getPhi(int number) {
    List<Long> primeFactors = Common.getPrimeFactors(number);
    if (primeFactors.size() == 1) {
      return number - 1;
    }
    int phi = (int) (number / primeFactors.get(0) * (primeFactors.get(0) - 1));
    for (int j = 1; j < primeFactors.size(); j++) {
      if (!primeFactors.get(j).equals(primeFactors.get(j - 1))) {
        phi = (int) (phi / primeFactors.get(j) * (primeFactors.get(j) - 1));
      }
    }
    return phi;
  }
}
