package com.example.taller_semana_santa;

import javafx.fxml.FXMLLoader;

public class ResourceLoader {
  public static FXMLLoader loadFXML(String path) {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(ResourceLoader.class.getResource(path));
    return fxmlLoader;
  }
}
