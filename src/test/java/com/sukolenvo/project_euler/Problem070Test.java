package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem070Test {

  @Test
  void run() {
    assertThat(new Problem070().run())
        .isEqualTo(-1);
  }
}