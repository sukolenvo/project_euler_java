package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    for (int i = 2; i <= base; i++) {
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

  static int divisorsSum(int number) {
    int result = 1;
    for (int i = 2; i <= Math.sqrt(number); i++) {
      if (number % i == 0) {
        result += number / i == i ? i : i + number / i;
      }
    }
    return result;
  }

  static Set<Integer> getDigits(int i) {
    Set<Integer> digits = new HashSet<>();
    while (i > 0) {
      digits.add(i % 10);
      i /= 10;
    }
    return digits;
  }

  static List<Integer> getDigitsList(long i) {
    List<Integer> digits = new ArrayList<>();
    while (i > 0) {
      digits.add((int) (i % 10));
      i /= 10;
    }
    return digits;
  }

  static int getLowestCommonTerm(int first, int second) {
    if (first == 1) {
      return second;
    }
    if (second == 1) {
      return first;
    }
    Map<Integer, Integer> common = new HashMap<>();
    Map<Long, Long> firstPrimes = getPrimeFactors(first).stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    firstPrimes
        .forEach((primeFactor, count) -> {
          common.merge(primeFactor.intValue(), count.intValue(), Math::max);
        });
    getPrimeFactors(second).stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .forEach((primeFactor, count) -> {
          common.merge(primeFactor.intValue(), count.intValue(), Math::max);
        });
    return common.entrySet().stream()
        .mapToInt(entry -> (int) Math.pow(entry.getKey(), entry.getValue()))
        .reduce(1, (a, b) -> a * b);
  }

  static int getDigitCount(int i) {
    if (i < 0) {
      throw new IllegalStateException("Negative number " + i);
    }
    if (i < 10) {
      return 1;
    }
    if (i < 100) {
      return 2;
    }
    if (i < 1000) {
      return 3;
    }
    if (i < 10000) {
      return 4;
    }
    if (i < 100000) {
      return 5;
    }
    if (i < 1_000_000) {
      return 6;
    }
    if (i < 10_000_000) {
      return 7;
    }
    if (i < 100_000_000) {
      return 8;
    }
    if (i < 1_000_000_000) {
      return 9;
    }
    return 10;
  }

  static int getDigitCount(long i) {
    if (i < 0) {
      throw new IllegalStateException("Negative number " + i);
    }
    if (i < 10) {
      return 1;
    }
    if (i < 100) {
      return 2;
    }
    if (i < 1000) {
      return 3;
    }
    if (i < 10000) {
      return 4;
    }
    if (i < 100000) {
      return 5;
    }
    if (i < 1_000_000) {
      return 6;
    }
    if (i < 10_000_000) {
      return 7;
    }
    if (i < 100_000_000) {
      return 8;
    }
    if (i < 1_000_000_000) {
      return 9;
    }
    return (int) (Math.log10(i) + 1);
  }
}
