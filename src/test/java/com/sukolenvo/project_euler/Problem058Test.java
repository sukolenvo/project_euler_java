package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem058Test {

  @Test
  void run() {
    assertThat(new Problem058().run(10))
        .as("search to ration <10")
        .isEqualTo(-1);
  }
}