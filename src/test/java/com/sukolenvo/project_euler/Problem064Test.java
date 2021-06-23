package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class Problem064Test {

  @Test
  void run() {
    assertThat(new Problem064().run())
        .isEqualTo(-1);
  }

  @Test
  void getPeriod() {
    assertThat(new Problem064().getPeriod(23))
        .as("check period of 23")
        .isEqualTo(List.of(1, 3, 1, 8));
  }
}