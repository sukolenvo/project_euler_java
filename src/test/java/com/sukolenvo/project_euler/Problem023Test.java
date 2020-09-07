package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;

class Problem023Test {

  @Test
  void run() {
    assertThat(new Problem023().run())
        .isEqualTo(0);
  }

  @Test
  void isSumOfTwo() {
    assertThat(new Problem023().isSumOfTwo(24, Set.of(12)))
        .as("check 24 is 12+12")
        .isTrue();

    assertThat(new Problem023().isSumOfTwo(3, Set.of(12)))
        .as("check 3 is not a sum")
        .isFalse();
  }
}