package com.example.models;

import java.util.List;

public class Cage {
  private String name;
  private List<Dog> dogs;

  private static int cageCounter = 0;

  public Cage(String name, List<Dog> dogs) {
    cageCounter++;
    this.name = name.concat("-" + "10" + cageCounter);
    this.dogs = dogs;
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
