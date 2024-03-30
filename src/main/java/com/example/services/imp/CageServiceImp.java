package com.example.services.imp;

import com.example.exceptions.CageException;
import com.example.models.Cage;
import com.example.models.Dog;
import com.example.services.CageService;

import java.util.LinkedList;
import java.util.Optional;

public class CageServiceImp implements CageService {
  private LinkedList<LinkedList<Cage>> cages = new LinkedList<>();

  public CageServiceImp() {
    generateCages();

  }

  @Override
  public void addDogToCage(Cage cage, Dog dog) {
    System.out.println("Adding dog to cage " + cage.getName());
    if(cage.getDogs().size() == 3) {
      throw new CageException("The cage is full");
    }

    Optional<Dog> optionalAlreadyRegisteredDog = getDogByNameAndOwnerId(dog.getName(), dog.getOwnerId());
    if(optionalAlreadyRegisteredDog.isPresent()) throw new CageException("The dog is already registered in a cage");

    Optional<Dog> optionalDogDifferentGender = cage.getDogs().stream().filter(d -> d.getGender() != dog.getGender()).findAny();
    if(optionalDogDifferentGender.isPresent()) throw new CageException("The cage has a dog of different gender " + optionalDogDifferentGender.get().getName() + " " + optionalDogDifferentGender.get().getGender());

    cage.getDogs().add(dog);
  }

  @Override
  public LinkedList<LinkedList<Cage>> getCages() {
    return cages;
  }

  private void generateCages() {
    for (int i = 0; i < 3; i++) {
      LinkedList<Cage> cageRow = new LinkedList<>();
      for (int j = 0; j < 3; j++) {
        cageRow.add(new Cage("J" + (i + 1), new LinkedList<>()));
      }
      cages.add(cageRow);
    }
  }

  private Optional<Dog> getDogByNameAndOwnerId(String name, String ownerId) {
    return cages.stream()
            .flatMap(LinkedList::stream)
            .flatMap(c -> c.getDogs().stream())
            .filter(d -> d.getName().equals(name) && d.getOwnerId().equals(ownerId))
            .findAny();
  }
}

