package com.example.taller_semana_santa;

import com.example.datastructures.LinkedList;
import com.example.models.MinesweeperRecordStat;
import com.example.utils.FileUtil;
import com.example.utils.MinesweeperUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MinesweeperSingleGame extends JFrame {
  private int rows;
  private int columns;
  private int bombs;
  private int score = 0; // Variable para almacenar el puntaje
  private JLabel scoreLabel = new JLabel("Puntuación: " + score);
  private com.example.datastructures.LinkedList<com.example.datastructures.LinkedList<Integer>> board = new LinkedList<>();

  private FileUtil<MinesweeperRecordStat> fileUtil = new FileUtil<>();
  private int record;
  private JLabel recordLabel = new JLabel();

  public MinesweeperSingleGame(int rows, int columns, int bombs) {
    this.rows = rows;
    this.columns = columns;
    this.bombs = bombs;
    initGame();
  }

  private void initGame() {
    MinesweeperUtil.initializeBoard(board, rows, columns, bombs);
    MinesweeperUtil.placeBombs(board, rows, columns, bombs);
    initializeRecord();
    addScoreLabel();
    drawBoard();
  }

  private void addScoreLabel() {
    scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
    getContentPane().add(scoreLabel, BorderLayout.SOUTH); // Agrega la etiqueta en la parte superior de la ventana
  }

  private void initializeRecord() {
    MinesweeperRecordStat record = fileUtil.readObjectFromFile("src/main/java/com/example/persistence/record.txt");
    if (record != null) {
      this.record = record.getScore();
      recordLabel.setFont(new Font("Arial", Font.BOLD, 16));
      this.recordLabel.setText("Record: " + this.record);
      getContentPane().add(recordLabel, BorderLayout.NORTH); // Agrega la etiqueta en la parte superior de la ventana
    }
  }




  public void drawBoard() {
    JPanel boardPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = 50; // Tamaño de cada celda
        for (int i = 0; i < rows; i++) {
          for (int j = 0; j < columns; j++) {
            if (board.get(i).get(j) == 1) {
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

    boardPanel.setPreferredSize(new Dimension(columns * 50, rows * 50)); // Ajusta el tamaño según tus necesidades

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
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void revealCell(int row, int col) {
    if (board.get(row).get(col) == -1) {
      System.out.println("Game Over");
      JOptionPane.showMessageDialog(null, "Game Over");
      dispose();
    } else if (board.get(row).get(col) == 1) {
      System.out.println("Cell already revealed");
    } else {
      board.get(row).set(col, 1);
      score++;
      drawBoard();
      scoreLabel.setText("Score: " + score);

      if (score > record) {
        record = score;
        recordLabel.setText("Record: " + record);
        fileUtil.writeObjectToFile("src/main/java/com/example/persistence/record.txt", new MinesweeperRecordStat("Player", record));
      }
    }

  }
}