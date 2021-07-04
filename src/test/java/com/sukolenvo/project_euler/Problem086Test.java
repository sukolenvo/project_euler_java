package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem086Test {

  @Test
  void run() {
    assertThat(new Problem086().generateCuboids(2060))
        .isEqualTo(100);
    assertThat(new Problem086().run())
        .isEqualTo(-1);
  }
}