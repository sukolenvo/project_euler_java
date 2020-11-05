package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem057Test {

  @Test
  void run() {
    assertThat(new Problem057().run(7))
        .as("no fraction match criteria within first 7 iteration")
        .isZero();

    assertThat(new Problem057().run(8))
        .as("8th iteration matches")
        .isOne();

    assertThat(new Problem057().run(1000))
        .as("check 1000 iterations")
        .isEqualTo(-1);
  }
}