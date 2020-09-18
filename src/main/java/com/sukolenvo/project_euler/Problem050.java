package com.sukolenvo.project_euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Problem050 {

  int run() {
    List<Integer> primes = new ArrayList<>();
    for (int i = 2; i < 1_000_000; i++) {
      if (BigInteger.valueOf(i).isProbablePrime(100)) {
        primes.add(i);
      }
    }
    int longestStreak = 21;
    int result = 953;
    for (int i = 1; i < primes.size(); i++) {
      int start = 0;
      int end = longestStreak - 1;
      int sum = primes.stream().limit(longestStreak).mapToInt(Integer::intValue).sum();
      while (end < i) {
        if (sum + primes.get(end + 1) <= primes.get(i)) {
          end++;
          sum += primes.get(end);
          if (sum == primes.get(i) && longestStreak < end - start + 1) {
            longestStreak = end - start + 1;
            result = sum;
          }
        } else {
          sum -= primes.get(start);
          start++;
          if (end - start < longestStreak) {
            break;
          }
        }
      }
    }
    return result;
  }
}
