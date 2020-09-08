package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem034Test {

  @Test
  void run() {
    assertThat(new Problem034().run())
        .isEqualTo(-1);
  }
}