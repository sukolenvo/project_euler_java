package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem117Test {

  @Test
  void run() {
    assertThat(new Problem117().run(5))
        .isEqualTo(15);
    assertThat(new Problem117().run(50))
        .isEqualTo(-1);
  }
}