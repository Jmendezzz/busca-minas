package com.example.taller_semana_santa;

import com.example.datastructures.LinkedList;
import com.example.utils.MinesweeperUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class MinesweeperCoopGame extends JFrame {

  private int initialBombs;
  private int rows;
  private int columns;
  private int bombs;
  private JLabel bombsLabel = new JLabel();
  private JLabel currentPlayerLabel = new JLabel("Turno del jugador: ");
  private JLabel player1BombsLabel = new JLabel("Jugador 1: ");
  private JLabel player2BombsLabel = new JLabel("Jugador 2: ");
  private JPanel infoPanel = new JPanel();
  private JPanel boardPanel;
  private LinkedList<LinkedList<Integer>> board = new LinkedList<>();
  private Integer currentPlayer = 1;
  private HashMap<Integer, Integer> playersBombs = new HashMap<>() {
    {
      put(1, 0);
      put(2, 0);
    }
  };

  public MinesweeperCoopGame(int rows, int columns, int bombs) {
    this.rows = rows;
    this.columns = columns;
    this.bombs = bombs;
    this.initialBombs = bombs;
    initGame();
  }

  private void initGame() {
    MinesweeperUtil.initializeBoard(board, rows, columns, bombs);
    MinesweeperUtil.placeBombs(board, rows, columns, bombs);
    drawInfoPanel();
    drawBoard();
  }

  private void drawInfoPanel() {
    infoPanel.setLayout(new GridLayout(2, 2));
    infoPanel.add(bombsLabel);
    infoPanel.add(currentPlayerLabel);
    infoPanel.add(player1BombsLabel);
    infoPanel.add(player2BombsLabel);
    bombsLabel.setText("Bombas: " + bombs);
    add(infoPanel, BorderLayout.NORTH);
  }

  private void drawBoard() {
    boardPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = 50;
        for (int i = 0; i < rows; i++) {
          for (int j = 0; j < columns; j++) {
            int value = board.get(i).get(j);
            if (value == 2) {
              g.setColor(Color.RED);
            } else if (value == 1) {
              g.setColor(Color.WHITE);
            } else {
              g.setColor(Color.LIGHT_GRAY);
            }
            g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
            g.setColor(Color.BLACK);
            g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);

          }
        }
      }
    };
    boardPanel.setPreferredSize(new Dimension(columns * 50, rows * 50));
    boardPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int cellSize = 50;
        int row = e.getY() / cellSize; // Calcula la fila de la celda clicada
        int column = e.getX() / cellSize; // Calcula la columna de la celda clicada

        System.out.println("Celda clicada: Fila " + row + ", Columna " + column);

        revealCell(row, column);
      }
    });
    getContentPane().add(boardPanel, BorderLayout.CENTER);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void revealCell(int row, int column) {
    if (board.get(row).get(column) == -1) {
      board.get(row).set(column, 2);
      bombs--;
      bombsLabel.setText("Bombas: " + bombs);
      playersBombs.put(currentPlayer, playersBombs.get(currentPlayer) + 1);

      if (bombs == 0) {
        dispose();
        showResults();
        showEndGameMenu();
      }
    } else if (board.get(row).get(column) == 1 || board.get(row).get(column) == 2) {
      System.out.println("Cell already revealed");
    } else {
      board.get(row).set(column, 1);
    }
    drawBoard();
    currentPlayerLabel.setText("Turno del jugador: " + currentPlayer);
    currentPlayerLabel.setForeground(currentPlayer == 1 ? Color.RED : Color.BLUE);
    player1BombsLabel.setText("Jugador 1: " + playersBombs.get(1));
    player2BombsLabel.setText("Jugador 2: " + playersBombs.get(2));

    this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
  }

  private void showEndGameMenu() {
    String[] options = {"Repetir ronda con configuración actual", "Regresar a la configuración de juego", "Regresar a la aplicación principal"};
    int choice = JOptionPane.showOptionDialog(null, "¿Qué desea hacer?", "Fin del juego", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    switch (choice) {
      case 0:
        bombs = initialBombs;
        playersBombs.put(1, 0);
        playersBombs.put(2, 0);
        board.clear();
        initGame();
        break;
      case 1:
        dispose();
        new MinesweeperSetupForm();
        break;
      case 2:
        dispose();
        break;
      default:
        break;
    }
  }

  private void showResults() {
    if (playersBombs.get(1) == playersBombs.get(2)) {
      JOptionPane.showMessageDialog(null, "Empate!", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    String winner = playersBombs.get(1) > playersBombs.get(2) ? "Jugador 1" : "Jugador 2";
    JOptionPane.showMessageDialog(null, "El " + winner + " ha ganado!", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
  }
}
