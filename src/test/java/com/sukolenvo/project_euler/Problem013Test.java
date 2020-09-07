package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem013Test {

  @Test
  void run() throws Exception {
    assertThat(new Problem013().run())
        .as("check result")
        .isEqualTo("0");
  }
}