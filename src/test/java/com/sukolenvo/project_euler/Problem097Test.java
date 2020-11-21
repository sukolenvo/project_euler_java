package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem097Test {

  @Test
  void run() {
    assertThat(new Problem097().run())
        .isEqualTo(-1);
  }
}