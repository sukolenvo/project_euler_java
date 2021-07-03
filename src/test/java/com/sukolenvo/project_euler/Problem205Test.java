package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class Problem205Test {

  @Test
  void run() {
    assertThat(new Problem205().run())
        .isEqualTo(BigDecimal.ZERO);
  }
}