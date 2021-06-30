package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class Problem078Test {

  @Test
  void run() {
    assertThat(new Problem078().run())
        .isEqualTo(-1);
  }

  @Test
  void calcPartitions() {
    BigInteger[] partitions = {BigInteger.ONE, BigInteger.ONE, BigInteger.valueOf(2), BigInteger.valueOf(3),
        BigInteger.valueOf(5)};
    assertThat(new Problem078().calcPartition(partitions, 5))
        .isEqualTo(7);
  }
}