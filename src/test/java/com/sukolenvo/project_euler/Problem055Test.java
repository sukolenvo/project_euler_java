package com.sukolenvo.project_euler;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem055Test {

  @Test
  void run() {
    assertThat(new Problem055().run())
        .isEqualTo(-1);
  }

  @Test
  void isLichrel() {
    assertThat(new Problem055().isLichrel(47))
        .as("check if 47 is lichrel")
        .isFalse();

    assertThat(new Problem055().isLichrel(349))
        .as("check if 349 is lichrel")
        .isFalse();

    assertThat(new Problem055().isLichrel(10677))
        .as("check if 10677 is lichrel for less then 50 steps")
        .isTrue();
  }

  @Test
  void isPalindrom() {
    assertThat(new Problem055().isPalindrom(BigInteger.valueOf(7337)))
        .as("check 7337 is palindrom")
        .isTrue();
  }
}