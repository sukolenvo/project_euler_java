package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem037Test {

  @Test
  void run() {
    assertThat(new Problem037().run())
        .isEqualTo(-1);
  }

  @Test
  void isTruncatablePrime() {
    assertThat(new Problem037().isTruncatablePrime(Common.getDigitsList(3797)))
        .as("check isTruncatablePrime")
        .isTrue();
  }
}