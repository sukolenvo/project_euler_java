package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem045Test {

  @Test
  void run() {
    assertThat(new Problem045().run())
        .isEqualTo(-1);
  }
}