package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem068Test {

  @Test
  void run() {
    assertThat(new Problem068().run())
        .isEqualTo(-1);
  }
}