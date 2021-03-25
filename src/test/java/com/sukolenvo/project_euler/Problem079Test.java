package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem079Test {

  @Test
  void run() throws Exception {
    assertThat(new Problem079().run())
        .as("check result")
        .isEqualTo(-1);
  }
}
