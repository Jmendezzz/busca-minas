package com.example.controllers;


import com.example.models.Cage;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Map;

public class CageCardController {

  @FXML
  private VBox cageBox;

  @FXML
  private Text cageName;
  private Map<Integer,String> colors = Map.of(3, "0000ff", 2, "ffff00");

  private Cage cage;

  public void setData(Cage cage) {
    this.cage = cage;
    cageName.setText(cage.getName());
    cageBox.setStyle("-fx-background-color: #" + colors.get(cage.getDogs().size()));
  }
}
