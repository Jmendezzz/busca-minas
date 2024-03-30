module com.example.taller_semana_santa {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.kordamp.bootstrapfx.core;
  requires java.desktop;

  opens com.example.taller_semana_santa to javafx.fxml;
  exports com.example.taller_semana_santa;
  exports com.example.controllers;
  opens com.example.controllers to javafx.fxml;
}