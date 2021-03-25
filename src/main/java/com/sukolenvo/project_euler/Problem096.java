package com.sukolenvo.project_euler;

import java.awt.Point;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import org.apache.commons.io.IOUtils;

public class Problem096 {

  int run() throws Exception {
    @Cleanup InputStream stream = getClass().getClassLoader().getResourceAsStream("problem096.txt");
    List<Sudoku> sudokus = parseSudoku(IOUtils.toString(stream, StandardCharsets.UTF_8));
    assert sudokus.size() == 50;
    sudokus.forEach(Sudoku::solve);
    return sudokus.stream()
        .mapToInt(sudoku -> sudoku.getPossibleValues(1, 1).getResolvedValue() * 100
            + sudoku.getPossibleValues(2, 1).getResolvedValue() * 10
            + sudoku.getPossibleValues(3, 1).getResolvedValue())
        .sum();
  }

  List<Sudoku> parseSudoku(String input) {
    String[] lines = input.split("\n");
    assert lines.length % 10 == 0;
    List<Sudoku> result = new ArrayList<>();
    for (int i = 0; i < lines.length;) {
      String title = lines[i++];
      int[] values = new int[81];
      for (int j = 0; j < 9; j++) {
        int[] line = lines[i++].trim().replaceAll(" ", "").chars()
            .map(c -> c - '0')
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
    private List<PossibleValues> possibleValues;

    Sudoku(String title, int[] values) {
      assert values.length == 81;
      this.title = title;
      possibleValues = Arrays.stream(values).mapToObj(value -> {
        if (value == 0) {
          return PossibleValues.newAllPossible();
        }
        return new PossibleValues(new HashSet<>(List.of(value)));
      })
          .collect(Collectors.toList());
    }

    Sudoku(Sudoku from) {
      this.title = from.getTitle();
      this.possibleValues = from.getPossibleValues().stream()
          .map(PossibleValues::new)
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
      return getBlockIndices(block)
          .map(coordinates -> getPossibleValues(coordinates.x, coordinates.y));
    }

    public Stream<Point> getBlockIndices(int block) {
      Builder<Point> builder = Stream.builder();
      int zeroBasedBlockIndex = block - 1;
      for (int i = zeroBasedBlockIndex * 3 % 9; i < zeroBasedBlockIndex * 3 % 9 + 3; i++) {
        for (int j = zeroBasedBlockIndex / 3 * 3 ; j < zeroBasedBlockIndex / 3 * 3 + 3; j++) {
          builder.accept(new Point(i + 1, j + 1));
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

    public void checkValid() {
      if (possibleValues.stream().anyMatch(possibleValues -> possibleValues.getDigits().isEmpty())) {
        throw new InvalidSudokuException("no possible values for sell");
      }
      for (int i = 1; i < 10; i++) {
        int index = i;
        List<PossibleValues> resolvedValues = getBlock(i)
            .filter(PossibleValues::isResolved)
            .collect(Collectors.toList());
        if (Set.copyOf(resolvedValues).size() < resolvedValues.size()) {
          throw new InvalidSudokuException("duplicates found in column" + index);
        }
        resolvedValues = IntStream.rangeClosed(1, 9)
            .mapToObj(j -> getPossibleValues(index, j))
            .filter(PossibleValues::isResolved)
            .collect(Collectors.toList());
        if (Set.copyOf(resolvedValues).size() < resolvedValues.size()) {
          throw new InvalidSudokuException("duplicates found in column" + index);
        }
        resolvedValues = IntStream.rangeClosed(1, 9)
            .mapToObj(j -> getPossibleValues(j, index))
            .filter(PossibleValues::isResolved)
            .collect(Collectors.toList());
        if (Set.copyOf(resolvedValues).size() < resolvedValues.size()) {
          throw new InvalidSudokuException("duplicates found in column" + index);
        }
      }
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

    void solve() {
      while (!isCompleted()) {
        checkValid();
        boolean changed = normalise();
        for (int i = 1; i < 10; i++) {
          changed |= onlyRowInBlock(i);
          changed |= onlyColumnInBlock(i);
          changed |= resolveOnlyPlaceInLine(i);
          changed |= resolveOnlyPlaceInColumn(i);
          changed |= resolveOnlyPlaceInBlock(i);
        }
        if (!changed) {
          guessAndTest();
        }
      }
    }

    void guessAndTest() {
      Sudoku branch = new Sudoku(this);
      int i = 0;
      for (; i < branch.getPossibleValues().size(); i++) {
        if (!branch.getPossibleValues().get(i).isResolved()) {
          break;
        }
      }
      int testResolvedValue = branch.getPossibleValues().get(i).getDigits().stream().findAny().orElseThrow();
      branch.getPossibleValues().get(i).setResolvedValue(testResolvedValue);
      try {
        branch.solve();
        possibleValues = branch.getPossibleValues();
      } catch (InvalidSudokuException e) {
        possibleValues.get(i).getDigits().remove(testResolvedValue);
      }
    }

    boolean onlyRowInBlock(int block) {
      assert block > 0 && block < 10;
      boolean changed = false;
      int[] row = new int[10];
      for (Point coordinates : getBlockIndices(block).collect(Collectors.toList())) {
        PossibleValues possibleValues = getPossibleValues(coordinates.x, coordinates.y);
        if (possibleValues.isResolved()) {
          row[possibleValues.getResolvedValue()] = -1;
        } else {
          for (Integer digit : possibleValues.getDigits()) {
            if (row[digit] == 0) {
              row[digit] = coordinates.y;
            } else if (row[digit] != coordinates.y) {
              row[digit] = -1;
            }
          }
        }
      }
      for (int i = 1; i < row.length; i++) {
        if (row[i] > 0) {
          int excludeFrom = getBlockIndices(block).mapToInt(point -> point.x).min().orElseThrow();
          int excludeTo = getBlockIndices(block).mapToInt(point -> point.x).max().orElseThrow();
          for (int j = 1; j < 10; j++) {
            if (j < excludeFrom || j > excludeTo) {
              changed |= getPossibleValues(j, row[i]).getDigits().remove(i);
            }
          }
        }
      }
      return changed;
    }

    boolean onlyColumnInBlock(int block) {
      assert block > 0 && block < 10;
      boolean changed = false;
      int[] column = new int[10];
      for (Point coordinates : getBlockIndices(block).collect(Collectors.toList())) {
        PossibleValues possibleValues = getPossibleValues(coordinates.x, coordinates.y);
        if (possibleValues.isResolved()) {
          column[possibleValues.getResolvedValue()] = -1;
        } else {
          for (Integer digit : possibleValues.getDigits()) {
            if (column[digit] == 0) {
              column[digit] = coordinates.x;
            } else if (column[digit] != coordinates.x) {
              column[digit] = -1;
            }
          }
        }
      }
      for (int i = 1; i < column.length; i++) {
        if (column[i] > 0) {
          int excludeFrom = getBlockIndices(block).mapToInt(point -> point.y).min().orElseThrow();
          int excludeTo = getBlockIndices(block).mapToInt(point -> point.y).max().orElseThrow();
          for (int j = 1; j < 10; j++) {
            if (j < excludeFrom || j > excludeTo) {
              changed |= getPossibleValues(column[i], j).getDigits().remove(i);
            }
          }
        }
      }
      return changed;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder(title);
      for (int i = 0; i < possibleValues.size(); i++) {
        if (i % 9 == 0) {
          builder.append("\n");
        }
        builder.append(possibleValues.get(i).isResolved() ? possibleValues.get(i).getResolvedValue() : 0);
      }
      return builder.toString();
    }
  }

  @Data
  @AllArgsConstructor
  static class PossibleValues {

    private Set<Integer> digits;

    PossibleValues(PossibleValues from) {
      this(new HashSet<>(from.digits));
    }

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
      digits = new HashSet<>(List.of(value));
    }
  }

  static class InvalidSudokuException extends RuntimeException {

    InvalidSudokuException(String message) {
      super(message);
    }
  }
}
