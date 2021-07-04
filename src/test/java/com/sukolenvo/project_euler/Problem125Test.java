package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem125Test {

  @Test
  void run() {
    assertThat(new Problem125().run(1000))
        .isEqualTo(4164L);
    assertThat(new Problem125().run(100_000_000))
        .isEqualTo(-1);
  }
}