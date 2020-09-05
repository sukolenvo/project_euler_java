package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Common {

  static int getFactorsCount(long number) {
    return getPrimeFactors(number).stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .values()
        .stream()
        .mapToInt(i -> (int) (i + 1))
        .reduce(1, (l, r) -> l * r);
  }

  static long getFactorial(int base) {
    int result = 1;
    for (int i = 2; i < base; i++) {
      result *= i;
    }
    return result;
  }

  static List<Long> getPrimeFactors(long number) {
    List<Long> result = new ArrayList<>();
    for (long i = 2; number >= 2;) {
      if (number % i == 0) {
        result.add(i);
        number /= i;
        i = 2;
      } else {
        i++;
      }
    }
    return result;
  }
}
