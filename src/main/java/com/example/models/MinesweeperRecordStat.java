package com.example.models;

import java.io.Serializable;

public class MinesweeperRecordStat implements Serializable {
  private String playerName;
  private int score;

  public MinesweeperRecordStat(String playerName, int score) {
    this.playerName = playerName;
    this.score = score;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
