package com.example.taller_semana_santa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

  public class MinesweeperSetupForm extends JFrame {
  private JTextField playersField;
  private JTextField rowsField;
  private JTextField columnsField;
  private JTextField bombsField;

  public MinesweeperSetupForm() {

    setTitle("Configuración de Buscaminas");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 250);
    setLocationRelativeTo(null); // Centra la ventana en la pantalla

    JPanel mainPanel = new JPanel(new GridLayout(5, 2));

    JLabel playersLabel = new JLabel("Número de jugadores:");
    mainPanel.add(playersLabel);
    playersField = new JTextField();
    mainPanel.add(playersField);

    JLabel rowsLabel = new JLabel("Número de filas:");
    mainPanel.add(rowsLabel);
    rowsField = new JTextField();
    mainPanel.add(rowsField);

    JLabel columnsLabel = new JLabel("Número de columnas:");
    mainPanel.add(columnsLabel);
    columnsField = new JTextField();
    mainPanel.add(columnsField);

    JLabel bombsLabel = new JLabel("Cantidad de bombas:");
    mainPanel.add(bombsLabel);
    bombsField = new JTextField();
    mainPanel.add(bombsField);

    JButton startButton = new JButton("Aceptar");
    startButton.addActionListener(e -> startGame());
    mainPanel.add(startButton);

    add(mainPanel);
    setVisible(true);
  }

  private void startGame() {
    try {
      int numPlayers = Integer.parseInt(playersField.getText());
      int rows = Integer.parseInt(rowsField.getText());
      int columns = Integer.parseInt(columnsField.getText());
      int bombs = Integer.parseInt(bombsField.getText());
      if (numPlayers <= 0 || rows <= 0 || columns <= 0 || bombs <= 0) {
        JOptionPane.showMessageDialog(this, "Todos los valores deben ser enteros positivos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      if( !(((rows * columns) / bombs ) >  (9 + (bombs * 2))) ){
        JOptionPane.showMessageDialog(this, "La cantidad de bombas debe ser menor a la mitad de la suma de las filas y columnas.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      if(numPlayers > 2){
        JOptionPane.showMessageDialog(this, "El número de jugadores debe ser 1 o 2.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      if(numPlayers > 1){
        new MinesweeperCoopGame(rows, columns, bombs);
      }else{
        new MinesweeperSingleGame(rows, columns, bombs);
      }

      dispose(); // Cierra el formulario
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Ingrese números válidos para todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}