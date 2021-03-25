package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem112Test {

  @Test
  void run() {
    assertThat(new Problem112().run())
        .as("check result")
        .isEqualTo(-1);
  }
}
