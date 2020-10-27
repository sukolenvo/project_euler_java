package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem054Test {

  @Test
  void run() throws IOException  {
    assertThat(new Problem054().run())
        .isEqualTo(-1);
  }
}