package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem015Test {

  @Test
  void run() {
    assertThat(new Problem015().run(2))
        .as("check 2x2")
        .isEqualTo(6);

    assertThat(new Problem015().run(20))
        .as("check 20x20")
        .isEqualTo(0);
  }
}