package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem016Test {

  @Test
  void run() {
    assertThat(new Problem016().run(15))
        .as("check 15")
        .isEqualTo(26);

    assertThat(new Problem016().run(1000))
        .as("check 1000")
        .isEqualTo(0);
  }
}