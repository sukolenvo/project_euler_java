package com.sukolenvo.project_euler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class Problem051 {

  private final int primeFamilySize;
  private int result = Integer.MAX_VALUE;

  int run() {
    if (primeFamilySize < 5) {
      throw new UnsupportedOperationException("THis code is optimised to look for large families only");
    }
    for (int i = 5; i < 10; i++) {
      System.out.println("Checking numbers with " + i + " digits for family of " + primeFamilySize);
      checkNumbersWithLength(i);
      if (result != Integer.MAX_VALUE) {
        return result;
      }
    }
    throw new UnsupportedOperationException("Numbers under 10^10 checked only");
  }

  private void checkNumbersWithLength(int digitCount) {
    FamilyPattern pattern = new FamilyPattern(new int[digitCount]);
    for (Integer i : createPatterns(digitCount)) {
      pattern.resetTo(i);
      checkPattern(pattern);
    }
  }

  List<Integer> createPatterns(int digitCount) {
    List<Integer> patterns = new ArrayList<>(1 << (digitCount + 1));
    for (int i = 1; i < 1 << (digitCount + 1) - 1; i++) {
      patterns.add(i);
    }
    return patterns;
  }

  private void checkPattern(FamilyPattern pattern) {
    if (!pattern.isFixed(0)) {
      return; // last digit can't be replaceable as number should be odd
    }
    int max = (int) Math.pow(10, pattern.fixedDigitIndices().count());
    int start = pattern.isFixed(pattern.digitCount() - 1) ? max / 10 : 0;
    Function<Integer, DigitFamily> factory = DigitFamily.factoryForPattern(primeFamilySize, pattern);
    for (int i = start; i < max; i++) {
      if ((i & 1) == 0) {
        continue;
      }
      if (i % 5 == 0) {
        continue;
      }
      DigitFamily digitFamily = factory.apply(i);
      int result = digitFamily.checkFamilyOfPrimes(this.result);
      if (result != 0) {
        foundCandidate(result);
      }
    }
  }

  private void foundCandidate(int result) {
    if (this.result > result) {
      System.out.println("Found candidate " + result);
      this.result = result;
    }
  }

  static class FamilyPattern {
    /**
     * Order is from lowest digit to higher.
     * 0 are fixed digits
     * 1 are replaceable digits of the number
     */
    private final int[] pattern;

    FamilyPattern(int... digitsLowToHigh) {
      pattern = digitsLowToHigh;
    }

    void resetTo(int number) {
      for (int i = 0; i < pattern.length; i++) {
        pattern[i] = number & 1;
        number >>= 1;
      }
    }

    IntStream fixedDigitIndices() {
      return IntStream.range(0, pattern.length)
          .filter(index -> pattern[index] == 0);
    }

    IntStream replaceableDigitIndices() {
      return IntStream.range(0, pattern.length)
          .filter(index -> pattern[index] == 1);
    }

    boolean isFixed(int index) {
      return pattern[index] == 0;
    }

    int digitCount() {
      return pattern.length;
    }
  }

  @Getter
  @RequiredArgsConstructor
  static class DigitFamily {

    private int fixed = 0;
    private int[] replaceableIndices;
    private int minNumber;
    private final int primeFamilySize;

    static Function<Integer, DigitFamily> factoryForPattern(int primeFamilySize, FamilyPattern pattern) {
      int minNumber = (int) Math.pow(10, pattern.digitCount() - 1);
      var replaceableIndices = pattern.replaceableDigitIndices().toArray();
      return fixedDigits -> {
        DigitFamily digitFamily = new DigitFamily(primeFamilySize);
        int[] buffer = {fixedDigits};
        pattern.fixedDigitIndices().forEach(index -> {
          digitFamily.fixed += Math.pow(10, index) * (buffer[0] % 10);
          buffer[0] /= 10;
        });
        digitFamily.replaceableIndices = replaceableIndices;
        digitFamily.minNumber = minNumber;
        return digitFamily;
      };
    }

    static DigitFamily createDigitFamily(int primeFamilySize, int fixedDigits, FamilyPattern pattern) {
      return factoryForPattern(primeFamilySize, pattern).apply(fixedDigits);
    }

    int checkFamilyOfPrimes(int max) {
      int nonPrimeAllowed = 10 - primeFamilySize;
      int minPrime = 0;
      for (int i = 0; i < 10 && nonPrimeAllowed >= 0; i++) {
        int sum = fixed;
        for (int replaceableIndex : replaceableIndices) {
          sum += Math.pow(10, replaceableIndex) * i;
        }
        if (sum < minNumber || sum > max || !BigInteger.valueOf(sum).isProbablePrime(100)) {
          nonPrimeAllowed--;
        } else if (minPrime == 0) {
          minPrime = sum;
        }
      }
      return nonPrimeAllowed >= 0 ? minPrime : 0;
    }
  }
}
