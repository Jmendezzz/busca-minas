package com.example.utils;

import java.io.*;

public class FileUtil<T> {
  public T readObjectFromFile(String path) {
    try {
      File file = new File(path);

      FileInputStream fileIn = new FileInputStream(file);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      T obj = (T) in.readObject();
      in.close();
      fileIn.close();
      return obj;
    } catch (IOException i) {
      i.printStackTrace();
      return null;
    } catch (ClassNotFoundException c) {
      System.out.println("Class not found");
      c.printStackTrace();
      return null;
    }
  }

  public void writeObjectToFile(String path, T obj) {
    try {

      File file = new File(path);
      FileOutputStream fileOut = new FileOutputStream(file);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(obj);

      out.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
