package com.sukolenvo.project_euler;

import java.math.BigInteger;
import lombok.Data;
import lombok.experimental.Accessors;

public class Problem066 {

  int run() {
    BigInteger maxX = BigInteger.valueOf(9);
    int d = 5;
    for (int i = 2; i < 1000; i++) {
      if (isSquare(i)) {
        continue;
      }
      BigInteger minX = findMinX(i);
      if (minX.compareTo(maxX) > 0) {
        maxX = minX;
        d = i;
      }
    }
    return d;
  }

  private boolean isSquare(int i) {
    int sqrt = (int) Math.sqrt(i);
    return sqrt * sqrt == i;
  }

  BigInteger findMinX(int d) {
    BigInteger p = BigInteger.valueOf(d).sqrt();
    BigInteger pPrev = BigInteger.ONE;
    BigInteger q = BigInteger.ONE;
    BigInteger qPrev = BigInteger.ZERO;
    ContinuedFraction fraction = new ContinuedFraction()
        .setN(1)
        .setD(p.intValue())
        .setSquare(d)
        .setIntSqrt(p.intValue());
    while (true) {
      if (p.pow(2).subtract(q.pow(2).multiply(BigInteger.valueOf(d))).equals(BigInteger.ONE)) {
        return p;
      }
      int nextA = fraction.getA();
      BigInteger nextP = p.multiply(BigInteger.valueOf(nextA)).add(pPrev);
      BigInteger nextQ = q.multiply(BigInteger.valueOf(nextA)).add(qPrev);
      pPrev = p;
      p = nextP;
      qPrev = q;
      q = nextQ;
      fraction = fraction.next();
    }
  }

  /**
   * n / sqrt(square) - d
   */
  @Data
  @Accessors(chain = true)
  private static class ContinuedFraction {
    private int square;
    private int intSqrt;
    private int n;
    private int d;

    int getA() {
      return (intSqrt + d) / getNextN();
    }

    private int getNextN() {
      return (square - d * d) / n;
    }

    ContinuedFraction next() {
      int nextN = getNextN();
      int a = getA();
      return new ContinuedFraction()
          .setSquare(square)
          .setIntSqrt(intSqrt)
          .setN(nextN)
          .setD(a * nextN - d);
    }
  }
}
