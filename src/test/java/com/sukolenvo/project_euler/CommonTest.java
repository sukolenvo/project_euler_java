package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CommonTest {

  @Test
  void getFactorsCount() {
    assertThat(Common.getFactorsCount(3))
        .as("check 3")
        .isEqualTo(2);

    assertThat(Common.getFactorsCount(6))
        .as("check 6")
        .isEqualTo(4);

    assertThat(Common.getFactorsCount(10))
        .as("check 10")
        .isEqualTo(4);

    assertThat(Common.getFactorsCount(28))
        .as("check 28")
        .isEqualTo(6);

    assertThat(Common.getFactorsCount(1024))
        .as("check 1024")
        .isEqualTo(11);
  }

  @Test
  void getPrimeFactors() {
    assertThat(Common.getPrimeFactors(2))
        .as("check 2")
        .isEqualTo(List.of(2L));

    assertThat(Common.getPrimeFactors(3))
        .as("check 3")
        .isEqualTo(List.of(3L));

    assertThat(Common.getPrimeFactors(4))
        .as("check 4")
        .isEqualTo(List.of(2L, 2L));

    assertThat(Common.getPrimeFactors(28))
        .as("check 2")
        .isEqualTo(List.of(2L, 2L, 7L));
  }

  @Test
  void lowestCommonTerm() {
    assertThat(Common.getLowestCommonTerm(64, 98))
        .as("check lowestCommonTerm for 64 and 98")
        .isEqualTo(3136);
  }
}