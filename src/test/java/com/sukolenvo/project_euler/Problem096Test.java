package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.sukolenvo.project_euler.Problem096.PossibleValues;
import com.sukolenvo.project_euler.Problem096.Sudoku;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class Problem096Test {

  @Test
  void parseSudoku() {
    String input = "Test\n"
        + "1 0 0 0 0 0 0 0 0\n"
        + "0 2 0 0 0 0 0 0 0\n"
        + "0 0 3 0 0 0 0 0 0\n"
        + "0 0 0 4 0 0 0 0 0\n"
        + "0 0 0 0 5 0 0 0 0\n"
        + "0 0 0 0 0 6 0 0 0\n"
        + "0 0 0 0 0 0 7 0 0\n"
        + "0 0 0 0 0 0 0 8 0\n"
        + "0 0 0 0 0 0 0 0 9\n";
    List<Sudoku> result = new Problem096().parseSudoku(input);
    assertThat(result)
        .as("check sudoku parsing")
        .hasSize(1);

    assertThat(result.get(0))
        .as("check sudoku parsing")
        .returns("Test", Sudoku::getTitle);

    for (int i = 1; i < 10; i++) {
      for (int j = 1; j < 10; j++) {
        if (i == j) {
          assertThat(result.get(0).getPossibleValues(i, j))
              .as("check known value (%d:%d)", i, j)
              .isEqualTo(new PossibleValues(Set.of(i)));
        } else {
          assertThat(result.get(0).getPossibleValues(i, j))
              .as("check unknown values")
              .isEqualTo(PossibleValues.newAllPossible());
        }
      }
    }
  }
}
