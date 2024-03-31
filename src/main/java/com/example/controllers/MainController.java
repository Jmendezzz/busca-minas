package com.example.controllers;

import com.example.taller_semana_santa.ResourceLoader;
import com.example.models.Cage;
import com.example.services.CageService;
import com.example.services.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MainController {
  private CageService cageService = Singleton.getInstance().getCageService();
  @FXML
  private VBox cageContainer;

  @FXML
  private Button playButton;


}