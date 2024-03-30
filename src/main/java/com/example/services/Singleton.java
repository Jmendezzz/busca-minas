package com.example.services;

import com.example.services.imp.CageServiceImp;

public class Singleton {
  private static Singleton instance = null;
  private CageService cageService;

  private Singleton() {
    cageService = new CageServiceImp();
  }

  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }

  public  CageService getCageService() {
    return cageService;
  }
}
