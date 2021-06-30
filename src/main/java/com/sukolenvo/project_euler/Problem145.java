package com.sukolenvo.project_euler;

public class Problem145 {

  int run(int size) {
    int result = 0;
    for (int i = 1; i < size; i++) {
      if (i % 10 == 0) {
        continue;
      }
      if (isOddSum(i) == Result.SUM_ODD) {
        result++;
      }
    }
    return result * 2;
  }

  Result isOddSum(int i) {
    int digitCount = Common.getDigitCount(i);
    int carry = 0;
    boolean checkHighLow = true;
    for (int j = 0; j < digitCount; j++) {
      int low = i / (int) (Math.pow(10, j)) % 10;
      int high = (i / (int) Math.pow(10, digitCount - 1 - j)) % 10;
      if (checkHighLow && high > low) {
        return Result.REVERSE_COMPLETED;
      }
      checkHighLow &= high == low;
      int nextSum = carry + low + high;
      carry = nextSum / 10;
      int nextDigit = nextSum % 10;
      if (nextDigit % 2 == 0) {
        return Result.SUM_EVEN;
      }
    }
    return Result.SUM_ODD;
  }

  enum Result {
    SUM_ODD,
    SUM_EVEN,
    /**
     * Status to indicate that reversed number already processed. E.g. no need to check 9876 as 6789 was already tested.
     */
    REVERSE_COMPLETED
  }
}
