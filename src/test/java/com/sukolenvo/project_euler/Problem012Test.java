package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem012Test {

  @Test
  void run() {
   assertThat(new Problem012().run())
       .as("check result")
       .isEqualTo(0);
  }
}