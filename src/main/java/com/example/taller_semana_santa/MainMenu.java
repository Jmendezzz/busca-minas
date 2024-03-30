package com.example.taller_semana_santa;

import com.example.models.Cage;
import com.example.services.CageService;
import com.example.services.Singleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class MainMenu extends JFrame {
  private JPanel cageContainer;
  private JButton playButton;
  private CageService cageService = Singleton.getInstance().getCageService();

  public MainMenu() {
    setTitle("Menú Principal");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(1035, 623);
    setLayout(new BorderLayout());

    cageContainer = new JPanel();
    cageContainer.setLayout(new BoxLayout(cageContainer, BoxLayout.Y_AXIS));
    cageContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JScrollPane scrollPane = new JScrollPane(cageContainer);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    add(scrollPane, BorderLayout.CENTER);

    playButton = new JButton("Jugar");
    playButton.setFont(new Font("Dungeon", Font.PLAIN, 22));
    add(playButton, BorderLayout.SOUTH);
    playButton.addActionListener(e -> {
      MinesweeperSetupForm setupForm = new MinesweeperSetupForm();
      setupForm.setVisible(true);
    });

    // Método para inicializar los elementos de la interfaz gráfica
    initialize();

    setVisible(true);
  }

  private void initialize() {
    LinkedList<LinkedList<Cage>> cages = cageService.getCages();


    for (int i = 0; i < cages.size(); i++) {
      LinkedList<Cage> row = cages.get(i);
      JPanel cageContainerRow = new JPanel();
      cageContainerRow.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
      for (Cage cage : row) {
        JPanel cageCard = new JPanel();
        cageCard.setPreferredSize(new Dimension(200, 100));
        cageCard.setBackground(Color.WHITE);
        JLabel cageNameLabel = new JLabel(cage.getName());
        cageNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        cageCard.add(cageNameLabel);
        cageCard.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            // Cuando se hace clic en la jaula, se abre el formulario de registro de perro
            DogRegistrationForm registrationForm = new DogRegistrationForm(cage, cageCard);
            registrationForm.setVisible(true);
          }
        });
        cageContainerRow.add(cageCard);
      }
      cageContainer.add(cageContainerRow);
    }
  }


  public static void main(String[] args) {
    SwingUtilities.invokeLater(MainMenu::new);
  }
}