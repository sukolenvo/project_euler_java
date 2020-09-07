package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem014Test {

  @Test
  void run() {
    assertThat(new Problem014().run())
        .isEqualTo(0);
  }
}