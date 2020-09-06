package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem029Test {

  @Test
  void run() {
    assertThat(new Problem029().run(5))
        .as("check 2 to 5")
        .isEqualTo(15);

    assertThat(new Problem029().run(100))
        .as("check 2 to 100")
        .isEqualTo(9183);
  }
}