package com.sukolenvo.project_euler;

import java.math.BigInteger;

public class Problem078 {

  long run() {
    BigInteger[] partitions = new BigInteger[1_000_000];
    partitions[0] = BigInteger.ONE;
    partitions[1] = BigInteger.ONE;
    for (int i = 2; i < partitions.length; i++) {
      partitions[i] = calcPartition(partitions, i);
      if (partitions[i].remainder(BigInteger.valueOf(1_000_000)).equals(BigInteger.ZERO)) {
        return i;
      }
    }
    throw new IllegalStateException("Not found");
  }

  BigInteger calcPartition(BigInteger[] partitions, int i) {
    BigInteger result = BigInteger.ZERO;
    int n = i - 1;
    int members = 1;
    int distance = 3;
    boolean addOrSubtract = true;
    while (n >= 0) {
      result = addOrSubtract ? result.add(partitions[n]) : result.subtract(partitions[n]);
      n -= members++;
      if (n >= 0) {
        result = addOrSubtract ? result.add(partitions[n]) : result.subtract(partitions[n]);
      }
      n -= distance;
      distance += 2;
      addOrSubtract = !addOrSubtract;
    }
    return result;
  }

}
