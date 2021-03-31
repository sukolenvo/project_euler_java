package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem065Test {

  @Test
  void run() {
    assertThat(new Problem065().run())
        .as("check result")
        .isEqualTo(-1);
  }
}
