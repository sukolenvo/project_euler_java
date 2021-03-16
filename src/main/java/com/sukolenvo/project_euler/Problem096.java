package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Data;

public class Problem096 {

  List<Sudoku> parseSudoku(String input) {
    String[] lines = input.split("\n");
    assert lines.length % 10 == 0;
    List<Sudoku> result = new ArrayList<>();
    for (int i = 0; i < lines.length;) {
      String title = lines[i++];
      int[] values = new int[81];
      for (int j = 0; j < 9; j++) {
        int[] line = Arrays.stream(lines[i++].split(" "))
            .map(String::trim)
            .mapToInt(Integer::parseInt)
            .toArray();
        assert line.length == 9;
        System.arraycopy(line, 0, values, j * 9, 9);
      }
      result.add(new Sudoku(title, values));
    }
    return result;
  }

  @Data
  static class Sudoku {

    private final String title;
    private final List<PossibleValues> possibleValues;

    Sudoku(String title, int[] values) {
      assert values.length == 81;
      this.title = title;
      possibleValues = Arrays.stream(values).mapToObj(value -> {
        if (value == 0) {
          return PossibleValues.newAllPossible();
        }
        return new PossibleValues(Set.of(value));
      })
          .collect(Collectors.toList());
    }

    public PossibleValues getPossibleValues(int x, int y) {
      assert x > 0 && x < 10;
      assert y > 0 && y < 10;
      return possibleValues.get(x * 9 + y - 10);
    }

    public boolean normaliseLine(int line) {
      assert line > 0 && line < 10;
      boolean updated = false;
      for (int i = 0; i < 9; i++) {
        PossibleValues possibleValues = this.possibleValues.get(line * 9 - 9 + i);
        if (possibleValues.isResolved()) {
          int resolvedValue = possibleValues.getResolvedValue();
          for (int j = 0; j < 9; j++) {
            if (i == j) {
              continue;
            }
            updated |= this.possibleValues.get(line * 9 - 9 +j).getDigits().remove(resolvedValue);
          }
        }
      }
      return updated;
    }
  }

  @Data
  static class PossibleValues {

    private final Set<Integer> digits;

    static PossibleValues newAllPossible() {
      return new PossibleValues(IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toSet()));
    }

    boolean isResolved() {
      return digits.size() == 1;
    }

    int getResolvedValue() {
      assert isResolved();
      return digits.iterator().next();
    }
  }
}
