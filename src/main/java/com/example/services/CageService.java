package com.example.services;

import com.example.datastructures.LinkedList;
import com.example.models.Cage;
import com.example.models.Dog;


public interface CageService {
  void addDogToCage(Cage cage, Dog dog);
  LinkedList<LinkedList<Cage>> getCages();
}
