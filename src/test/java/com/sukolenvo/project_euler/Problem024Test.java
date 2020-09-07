package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem024Test {

  @Test
  void run() {
    assertThat(new Problem024().run())
        .isEqualTo(0);
  }
}