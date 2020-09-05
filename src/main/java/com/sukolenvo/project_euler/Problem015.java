package com.sukolenvo.project_euler;

public class Problem015 {

  long run(int gridSize) {
    long[][] grid = new long[gridSize + 1][gridSize + 1];
    grid[0][0] = 1;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (i == 0 && j == 0) {
          continue;
        }
        if (i == 0) {
          grid[i][j] = grid[i][j - 1];
          continue;
        }
        if (j == 0) {
          grid[i][j] = grid[i - 1][j];
          continue;
        }
        grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
      }
    }
    return grid[gridSize][gridSize];
  }
}
