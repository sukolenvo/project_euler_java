package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem026Test {

  @Test
  void run() {
    assertThat(new Problem026().run())
        .isEqualTo(983);
  }

  @Test
  void getPeriod() {
    assertThat(new Problem026().getPeriod(1, 7))
        .isEqualTo(6);
  }
}