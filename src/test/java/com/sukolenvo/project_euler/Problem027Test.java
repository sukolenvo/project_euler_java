package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem027Test {

  @Test
  void run() {
    assertThat(new Problem027().run())
        .isEqualTo(0);
  }
}