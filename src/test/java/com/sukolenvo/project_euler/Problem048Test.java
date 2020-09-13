package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem048Test {

  @Test
  void run() {
    assertThat(new Problem048().run())
        .isEqualTo(-1);
  }
}