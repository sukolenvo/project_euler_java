package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem051Test {

  @Test
  void run() {
    assertThat(new Problem051(7).run())
        .as("check 7 according to task definition")
        .isEqualTo(56003);
    assertThat(new Problem051(8).run())
        .as("find 8 primes family")
        .isEqualTo(-1);

    // example of eight prime family is 5074009
  }

  @Test
  void familyPattern() {
    Problem051.FamilyPattern pattern = new Problem051.FamilyPattern(0, 1, 1, 0, 0);
    assertThat(pattern.fixedDigitIndices().toArray())
        .as("check fixed")
        .containsExactly(0, 3, 4);
    assertThat(pattern.replaceableDigitIndices().toArray())
        .as("check replaceable")
        .containsExactly(1, 2);
  }

  @Test
  void digitFamily() {
    Problem051.DigitFamily digitFamily = Problem051.DigitFamily.createDigitFamily(7, 563, new Problem051.FamilyPattern(0, 1, 1, 0, 0));
    assertThat(digitFamily)
        .as("check internal state")
        .returns(10_000, Problem051.DigitFamily::getMinNumber)
        .returns(56003, Problem051.DigitFamily::getFixed);
    assertThat(digitFamily.checkFamilyOfPrimes(60000))
        .as("check min prime")
        .isEqualTo(56003);
  }

  @Test
  void createPatterns() {
    assertThat(new Problem051(8).createPatterns(3))
        .as("check patterns factory")
        .isEqualTo(IntStream.rangeClosed(1, 7).boxed().collect(Collectors.toList()));
  }
}