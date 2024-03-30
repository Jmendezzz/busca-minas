package com.example.models;

public class Dog {
  private String name;
  private String ownerId;
  private Gender gender;

  public Dog(String name, String ownerId, Gender gender) {
    this.name = name;
    this.ownerId = ownerId;
    this.gender = gender;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }
}
