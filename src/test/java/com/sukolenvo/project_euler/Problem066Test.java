package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class Problem066Test {

  @Test
  void run() {
    assertThat(new Problem066().findMinX(7))
        .isEqualTo(BigInteger.valueOf(8));
    assertThat(new Problem066().run())
        .isEqualTo(-1);
  }
}