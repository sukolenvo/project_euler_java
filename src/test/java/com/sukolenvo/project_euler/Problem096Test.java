package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;

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

  @Test
  void normaliseLine() {
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
    Sudoku sudoku = new Problem096().parseSudoku(input).get(0);
    int line = 3;
    assertThat(sudoku.normaliseLine(line))
        .as("expecting normalise line to indicate sudoku changed")
        .isTrue();

    for (int i = 1; i < 10; i++) {
      PossibleValues possibleValues = sudoku.getPossibleValues(line, i);
      if (i == line) {
        assertThat(possibleValues)
            .as("check resolved column unchanged")
            .isEqualTo(new PossibleValues(Set.of(line)));
      } else {
        assertThat(possibleValues.getDigits())
            .as("check possible values normalised for column %d", i)
            .hasSize(8)
            .doesNotContain(line);
      }
    }

    assertThat(sudoku.normaliseLine(line))
        .as("no more changes to sudoku")
        .isFalse();
  }

  @Test
  void normaliseColumn() {
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
    Sudoku sudoku = new Problem096().parseSudoku(input).get(0);
    int column = 7;
    assertThat(sudoku.normaliseColumn(column))
        .as("expecting normalise line to indicate sudoku changed")
        .isTrue();

    for (int i = 1; i < 10; i++) {
      PossibleValues possibleValues = sudoku.getPossibleValues(i, column);
      if (i == column) {
        assertThat(possibleValues)
            .as("check resolved value unchanged")
            .isEqualTo(new PossibleValues(Set.of(column)));
      } else {
        assertThat(possibleValues.getDigits())
            .as("check possible values normalised for line %d", i)
            .hasSize(8)
            .doesNotContain(column);
      }
    }

    assertThat(sudoku.normaliseColumn(column))
        .as("no more changes to sudoku")
        .isFalse();
  }
}
