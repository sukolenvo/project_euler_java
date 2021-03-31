package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem071Test {

  @Test
  void run() {
    assertThat(new Problem071().run())
        .as("check result")
        .isEqualTo(-1);
  }
}
