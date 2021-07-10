package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class Problem110Test {

  @Test
  void run() {
    assertThat(new Problem110().run(4_000_000))
        .isEqualTo(BigInteger.ONE);
  }

  @Test
  void compare108() {
    assertThat(new Problem110().run(1000))
        .isEqualTo(BigInteger.ONE);
  }
}