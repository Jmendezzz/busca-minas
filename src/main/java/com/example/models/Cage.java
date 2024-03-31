package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class Cage {
  private String name;
  private List<Dog> dogs;

  private static int cageCounter = 0;

  public Cage(String name) {
    cageCounter++;
    this.name = name.concat("-" + "10" + cageCounter);
    this.dogs = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Dog> getDogs() {
    return dogs;
  }

  public void setDogs(List<Dog> dogs) {
    this.dogs = dogs;
  }


}
