package com.example.services;

import com.example.models.Cage;
import com.example.models.Dog;

import java.util.LinkedList;

public interface CageService {
  void addDogToCage(Cage cage, Dog dog);
  LinkedList<LinkedList<Cage>> getCages();
}
