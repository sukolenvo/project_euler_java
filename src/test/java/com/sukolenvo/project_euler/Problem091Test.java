package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Problem091Test {

  @Test
  void run() {
    assertThat(new Problem091().run(2))
        .isEqualTo(14);
    assertThat(new Problem091().run(5))
        .isEqualTo(101);
    assertThat(new Problem091().run(50))
        .isEqualTo(-1);
  }
}