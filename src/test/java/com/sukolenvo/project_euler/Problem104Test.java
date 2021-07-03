package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class Problem104Test {

  @Test
  void run() {
    assertThat(new Problem104().checkLastNine(839725641L))
        .isTrue();
    assertThat(new Problem104().check(BigInteger.valueOf(123456789)))
        .isTrue();
    assertThat(new Problem104().check(BigInteger.valueOf(1234567890123456789L)))
        .isTrue();
    assertThat(new Problem104().run())
        .isEqualTo(-1);
  }
}