package com.example.taller_semana_santa;

import com.example.exceptions.CageException;
import com.example.models.Cage;
import com.example.models.Dog;
import com.example.models.Gender;
import com.example.services.CageService;
import com.example.services.Singleton;

import javax.swing.*;
import java.awt.*;

public class DogRegistrationForm extends JFrame {
  private JTextField nameField;
  private JTextField ownerIdField;
  private JComboBox<Gender> genderComboBox;
  private JButton registerButton;

  private Cage cageSelected;

  private CageService cageService = Singleton.getInstance().getCageService();
  public DogRegistrationForm(Cage cageSelected, JPanel cageCard) {
    this.cageSelected = cageSelected;
    setLocationRelativeTo(null);
    setTitle("Registro de Perro");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(300, 200);
    setLayout(new BorderLayout());

    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(4, 1, 10, 10));
    formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel nameLabel = new JLabel("Nombre:");
    nameField = new JTextField();
    formPanel.add(nameLabel);
    formPanel.add(nameField);

    JLabel ownerIdLabel = new JLabel("ID del Propietario:");
    ownerIdField = new JTextField();
    formPanel.add(ownerIdLabel);
    formPanel.add(ownerIdField);

    JLabel genderLabel = new JLabel("Género:");
    genderComboBox = new JComboBox<>(Gender.values());
    formPanel.add(genderLabel);
    formPanel.add(genderComboBox);

    registerButton = new JButton("Registrar");
    registerButton.addActionListener(e -> {
      // Aquí puedes agregar la lógica para registrar el perro
      String name = nameField.getText();
      String ownerId = ownerIdField.getText();
      Gender gender = (Gender) genderComboBox.getSelectedItem();
      Dog dog = new Dog(name, ownerId, gender);

      if (name.isEmpty() || ownerId.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Los campos Nombre y ID del Propietario son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      try{
        cageService.addDogToCage(cageSelected, dog);
        cageCard.setBackground(getCageColor(cageSelected.getDogs().size()));
        cageCard.repaint();
        JOptionPane.showMessageDialog(null, "Perro registrado con éxito en la jaula.");
      }catch (CageException ex){
        JOptionPane.showMessageDialog(null, "Error al agregar el perro a la jaula: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
      dispose();
    });
    formPanel.add(registerButton);

    add(formPanel, BorderLayout.CENTER);

    setVisible(true);
  }

  private Color getCageColor(int numDogs) {
    if(numDogs == 2) return Color.YELLOW;
    if(numDogs == 3) return Color.BLUE;
    return Color.WHITE;
  }
}