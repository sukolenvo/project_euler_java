package com.sukolenvo.project_euler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

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
      PossibleValues possibleValues = sudoku.getPossibleValues(i, line);
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
      PossibleValues possibleValues = sudoku.getPossibleValues(column, i);
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

  @Test
  void normaliseBlock() {
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
    int block = 5;

    assertThat(sudoku.normaliseBlock(block))
        .as("expecting normalise line to indicate sudoku changed")
        .isTrue();

    // check block #5
    for (int i = 4; i <= 6; i++) {
      for (int j = 4; j <= 6; j++) {
        if (i == j) {
          assertThat(sudoku.getPossibleValues(i, j))
              .as("check resolved not changed for (%d:%d)", i, j)
              .returns(true, PossibleValues::isResolved);
        } else {
          assertThat(sudoku.getPossibleValues(i, j).getDigits())
              .as("check possible values normalised for (%d:%d)", i, j)
              .hasSize(6)
              .doesNotContain(4)
              .doesNotContain(5)
              .doesNotContain(6);
        }
      }
    }
    assertThat(sudoku.normaliseBlock(block))
        .as("no more changes to sudoku")
        .isFalse();
  }

  @Test
  void resolveOnlyPlaceInLine() {
    String input = "Test\n"
        + "0 0 0 0 0 0 0 1 0\n"
        + "0 2 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 2 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 2\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 2 0 0 0 0\n"
        + "0 0 0 0 0 0 2 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n";
    Sudoku sudoku = new Problem096().parseSudoku(input).get(0);
    sudoku.normalise();

    assertThat(sudoku.resolveOnlyPlaceInLine(1))
        .as("expecting to update sudoku")
        .isTrue();

    assertThat(sudoku.getPossibleValues(6, 1).getDigits())
        .as("check cell resolved")
        .isEqualTo(Set.of(2));
  }

  @Test
  void resolveOnlyPlaceInColumn() {
    String input = "Test\n"
        + "0 0 0 0 0 0 0 1 0\n"
        + "0 2 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 2 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 2\n"
        + "1 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 2 0 0 0 0\n"
        + "0 0 0 0 0 0 2 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n";
    Sudoku sudoku = new Problem096().parseSudoku(input).get(0);
    sudoku.normalise();

    assertThat(sudoku.resolveOnlyPlaceInColumn(1))
        .as("expecting to update sudoku")
        .isTrue();

    assertThat(sudoku.getPossibleValues(1, 9).getDigits())
        .as("check cell resolved")
        .isEqualTo(Set.of(2));
  }

  @Test
  void resolveOnlyPlaceInBlock() {
    String input = "Test\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 2 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 2 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 2\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 2 0 0 0 0\n"
        + "2 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 3 0\n";
    Sudoku sudoku = new Problem096().parseSudoku(input).get(0);
    sudoku.normalise();

    assertThat(sudoku.resolveOnlyPlaceInBlock(9))
        .as("expecting to update sudoku")
        .isTrue();

    assertThat(sudoku.getPossibleValues(7, 9).getDigits())
        .as("check cell resolved")
        .isEqualTo(Set.of(2));
  }

  @Test
  void normaliseColumn_last() {
    String input = "Test\n"
        + "0 0 0 0 0 0 0 1 0\n"
        + "0 2 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 2 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 2\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 2 0 0 0 0\n"
        + "0 0 0 0 0 0 2 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n";
    Sudoku sudoku = new Problem096().parseSudoku(input).get(0);
    assertThat(sudoku.normaliseColumn(9))
        .as("check sudoku updated")
        .isTrue();

    assertThat(sudoku.getPossibleValues(9, 1).getDigits())
        .as("expecting to update sudoku")
        .doesNotContain(2);
  }

  @Test
  void solve01() {
    String input = "Grid 01\n"
        + "003020600\n"
        + "900305001\n"
        + "001806400\n"
        + "008102900\n"
        + "700000008\n"
        + "006708200\n"
        + "002609500\n"
        + "800203009\n"
        + "005010300";
    Sudoku sudoku = new Problem096().parseSudoku(input).get(0);
    assertThatNoException()
        .as("check sudoku solved, but got %s", sudoku)
        .isThrownBy(sudoku::solve);

    System.out.println("Sudoku solved: " + sudoku);
  }

  @Test
  void onlyRowInBlock() {
    String input = "Test\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 2 0 0 0 0 0 0 9\n"
        + "0 0 0 0 0 0 3 7 1\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n"
        + "0 0 0 0 0 0 0 0 0\n";
    Sudoku sudoku = new Problem096().parseSudoku(input).get(0);
    sudoku.normalise();
    assertThat(sudoku.onlyRowInBlock(3))
        .as("check sudoku updated")
        .isTrue();

    assertThat(sudoku.getPossibleValues(4, 1).getDigits())
        .as("expecting to update sudoku")
        .doesNotContain(2);
    assertThat(sudoku.getPossibleValues(5, 1).getDigits())
        .as("expecting to update sudoku")
        .doesNotContain(2);
    assertThat(sudoku.getPossibleValues(6, 1).getDigits())
        .as("expecting to update sudoku")
        .doesNotContain(2);
  }

  @Test
  void solve() throws Exception {
    assertThat(new Problem096().run())
        .as("check problem result")
        .isEqualTo(-1);
  }
}
