package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem028Test {

  @Test
  void run() {
    assertThat(new Problem028().run(5))
        .as("check 5x5")
        .isEqualTo(101);

    assertThat(new Problem028().run(1001))
        .as("check 1001x1001")
        .isEqualTo(0);
  }
}