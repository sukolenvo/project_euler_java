package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem119Test {

  @Test
  void run() {
    assertThat(new Problem119().run(10))
        .isEqualTo(614656L);
    assertThat(new Problem119().run(30))
        .isEqualTo(-1);
  }
}