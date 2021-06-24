package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem089Test {

  @Test
  void run() throws Exception {
    assertThat(new Problem089().run())
        .isEqualTo(-1);
  }
}