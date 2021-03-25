package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.sukolenvo.project_euler.Problem099.IndexNumber;
import org.junit.jupiter.api.Test;

class Problem099Test {

  @Test
  void run() throws Exception {
    assertThat(new Problem099().run())
        .as("check result")
        .isEqualTo(-1);
  }

  @Test
  void compare() {
    assertThat(new IndexNumber(2, 11).compareTo(new IndexNumber(3, 7)))
        .as("check simple example")
        .isLessThan(0);

    assertThat(new IndexNumber(632382, 518061).compareTo(new IndexNumber(519432, 525806)))
        .as("check simple example")
        .isGreaterThan(0);
  }
}
