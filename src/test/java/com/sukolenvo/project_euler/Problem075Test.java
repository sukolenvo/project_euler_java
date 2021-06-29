package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Problem075Test {

  @Test
  void run() {
    assertThat(new Problem075().run())
        .isEqualTo(-1);
  }

  @Test
  void generateTriangles() {
    int[] triangles = new Problem075().generateTriangles();
    assertThat(triangles[12])
        .isEqualTo(1);
    assertThat(triangles[24])
        .isEqualTo(1);
    assertThat(triangles[30])
        .isEqualTo(1);
    assertThat(triangles[36])
        .isEqualTo(1);
    assertThat(triangles[40])
        .isEqualTo(1);
    assertThat(triangles[48])
        .isEqualTo(1);
    assertThat(triangles[120])
        .isGreaterThan(1);
    assertThat(triangles[20])
        .isZero();
  }
}