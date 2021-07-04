package com.sukolenvo.project_euler;

import java.util.stream.LongStream;

public class Problem125 {

  long run(int size) {
    int[] squares = new int[(int) Math.sqrt(size) + 1];
    for (int i = 0; i < squares.length; i++) {
      squares[i] = i * i;
    }
    boolean[] palindroms = new boolean[size];
    for (int i = 1; i < squares.length - 1; i++) {
      int sum = squares[i];
      for (int j = i + 1; j < squares.length; j++) {
        sum += squares[j];
        if (sum < size) {
          if (Common.isPalindrom(Common.getDigitsList(sum))) {
            palindroms[sum] = true;
          }
        } else {
          break;
        }
      }
    }
    return LongStream.range(0, palindroms.length)
        .filter(i -> palindroms[(int) i])
        .sum();
  }
}
