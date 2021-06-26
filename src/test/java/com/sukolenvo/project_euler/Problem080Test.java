package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem080Test {

  @Test
  void run() {
    assertThat(new Problem080().getDigitsSum(2))
        .isEqualTo(475);

    assertThat(new Problem080().run())
        .isEqualTo(-1);
  }
}