package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem039Test {

  @Test
  void run() {
    assertThat(new Problem039().run())
        .isEqualTo(-1);
  }

  @Test
  void countSolutions() {
    assertThat(new Problem039().countSolutions(120))
        .as("check number of solutions for 120")
        .isEqualTo(3);
  }
}