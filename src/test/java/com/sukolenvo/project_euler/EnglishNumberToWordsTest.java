package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EnglishNumberToWordsTest {

  @Test
  void convert() {
    assertThat(EnglishNumberToWords.convert(342))
        .as("check 342")
        .isEqualTo("three hundred and forty-two");

    assertThat(EnglishNumberToWords.convert(115))
        .as("check 115")
        .isEqualTo("one hundred and fifteen");
  }
}