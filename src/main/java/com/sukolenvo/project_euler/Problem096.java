package com.sukolenvo.project_euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import lombok.AllArgsConstructor;
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
      return possibleValues.get(y * 9 + x - 10);
    }

    public boolean normaliseLine(int line) {
      assert line > 0 && line < 10;
      boolean changed = false;
      for (int i = 0; i < 9; i++) {
        PossibleValues possibleValues = this.possibleValues.get(line * 9 - 9 + i);
        if (possibleValues.isResolved()) {
          int resolvedValue = possibleValues.getResolvedValue();
          for (int j = 0; j < 9; j++) {
            if (i == j) {
              continue;
            }
            changed |= this.possibleValues.get(line * 9 - 9 + j).getDigits().remove(resolvedValue);
          }
        }
      }
      return changed;
    }

    public boolean normaliseColumn(int column) {
      assert column > 0 && column < 10;
      boolean changed = false;
      for (int i = 0; i < 9; i++) {
        PossibleValues possibleValues = getPossibleValues(column, i + 1);
        if (possibleValues.isResolved()) {
          int resolvedValue = possibleValues.getResolvedValue();
          for (int j = 0; j < 9; j++) {
            if (i == j) {
              continue;
            }
            changed |= getPossibleValues(column, j + 1).getDigits().remove(resolvedValue);
          }
        }
      }
      return changed;
    }

    public boolean normaliseBlock(int block) {
      assert block > 0 && block < 10;
      boolean changed = false;
      for (PossibleValues possibleValues : getBlock(block).collect(Collectors.toList())) {
        if (possibleValues.isResolved()) {
          for (PossibleValues forUpdate : getBlock(block).collect(Collectors.toList())) {
            if (!forUpdate.isResolved()) {
              changed |= forUpdate.getDigits().remove(possibleValues.getResolvedValue());
            }
          }
        }
      }
      return changed;
    }

    public Stream<PossibleValues> getBlock(int block) {
      Builder<PossibleValues> builder = Stream.builder();
      int zeroBasedBlockIndex = block - 1;
      for (int i = zeroBasedBlockIndex * 3 % 9; i < zeroBasedBlockIndex * 3 % 9 + 3; i++) {
        for (int j = zeroBasedBlockIndex / 3 * 3 ; j < zeroBasedBlockIndex / 3 * 3 + 3; j++) {
          builder.accept(getPossibleValues(i + 1, j + 1));
        }
      }
      return builder.build();
    }

    public boolean normalise() {
      boolean changed = false;
      for (int i = 1; i < 10; i++) {
        changed |= normaliseLine(i);
        changed |= normaliseColumn(i);
        changed |= normaliseBlock(i);
      }
      return changed;
    }

    public boolean isCompleted() {
      return possibleValues.stream().allMatch(PossibleValues::isResolved);
    }

    public boolean resolveOnlyPlaceInLine(int line) {
      assert line > 0 && line < 10;
      int[] positions = new int[10];
      boolean changed = false;
      for (int i = 1; i < 10; i++) {
        PossibleValues possibleValues = getPossibleValues(i, line);
        if (possibleValues.isResolved()) {
          positions[possibleValues.getResolvedValue()] = -1;
        } else {
          for (int possibleValue : possibleValues.getDigits()) {
            if (positions[possibleValue] == 0) {
              positions[possibleValue] = i;
            } else if (positions[possibleValue] > 0) {
              positions[possibleValue] = -1;
            }
          }
        }
      }
      for (int i = 1; i < positions.length; i++) {
        if (positions[i] > 0) {
          getPossibleValues(positions[i], line).setResolvedValue(i);
          changed = true;
        }
      }
      return changed;
    }

    public boolean resolveOnlyPlaceInColumn(int column) {
      assert column > 0 && column < 10;
      int[] positions = new int[10];
      boolean changed = false;
      for (int i = 1; i < 10; i++) {
        PossibleValues possibleValues = getPossibleValues(column, i);
        if (possibleValues.isResolved()) {
          positions[possibleValues.getResolvedValue()] = -1;
        } else {
          for (int possibleValue : possibleValues.getDigits()) {
            if (positions[possibleValue] == 0) {
              positions[possibleValue] = i;
            } else if (positions[possibleValue] > 0) {
              positions[possibleValue] = -1;
            }
          }
        }
      }
      for (int i = 1; i < positions.length; i++) {
        if (positions[i] > 0) {
          getPossibleValues(column, positions[i]).setResolvedValue(i);
          changed = true;
        }
      }
      return changed;
    }

    public boolean resolveOnlyPlaceInBlock(int block) {
      assert block > 0 && block < 10;
      int[] positions = new int[10];
      boolean changed = false;
      List<PossibleValues> blockPossibleValues = getBlock(block).collect(Collectors.toList());
      for (int i = 1; i < 10; i++) {
        PossibleValues possibleValues = blockPossibleValues.get(i - 1);
        if (possibleValues.isResolved()) {
          positions[possibleValues.getResolvedValue()] = -1;
        } else {
          for (int possibleValue : possibleValues.getDigits()) {
            if (positions[possibleValue] == 0) {
              positions[possibleValue] = i;
            } else if (positions[possibleValue] > 0) {
              positions[possibleValue] = -1;
            }
          }
        }
      }
      for (int i = 1; i < positions.length; i++) {
        if (positions[i] > 0) {
          blockPossibleValues.get(positions[i] - 1).setResolvedValue(i);
          changed = true;
        }
      }
      return changed;
    }
  }

  @Data
  @AllArgsConstructor
  static class PossibleValues {

    private Set<Integer> digits;

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

    void setResolvedValue(int value) {
      assert digits.contains(value);
      digits = Set.of(value);
    }
  }
}
