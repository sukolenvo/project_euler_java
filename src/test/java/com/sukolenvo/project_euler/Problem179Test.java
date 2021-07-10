package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem179Test {

  @Test
  void run() {
    assertThat(Common.getFactorsCount(14))
        .isEqualTo(4);
    assertThat(new Problem179().run())
        .isEqualTo(-1);
  }
}