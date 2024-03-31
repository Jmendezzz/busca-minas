package com.example.utils;

import com.example.datastructures.LinkedList;

public class MinesweeperUtil {
  public static void initializeBoard(LinkedList<LinkedList<Integer>> board, int rows, int columns, int bombs) {
    for (int i = 0; i < rows; i++) {
      LinkedList<Integer> row = new LinkedList<>();
      for (int j = 0; j < columns; j++) {
        row.add(0);
      }
      board.add(row);
    }
  }

  public static void placeBombs(LinkedList<LinkedList<Integer>> board, int rows, int columns, int bombs ) {
    int bombsPlaced = 0;
    while (bombsPlaced < bombs) {
      int row = (int) (Math.random() * rows);
      int col = (int) (Math.random() * columns);

      int BOMB_IDENTIFIER = -1;
      if (board.get(row).get(col) != BOMB_IDENTIFIER) {

        board.get(row).set(col, BOMB_IDENTIFIER);
        bombsPlaced++;

      }
    }
  }
}
