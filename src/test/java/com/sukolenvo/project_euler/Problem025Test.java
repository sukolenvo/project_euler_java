package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem025Test {

  @Test
  void run() {
    assertThat(new Problem025().run())
        .isEqualTo(4782L);
  }
}