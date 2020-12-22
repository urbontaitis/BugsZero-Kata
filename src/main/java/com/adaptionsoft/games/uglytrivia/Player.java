package com.adaptionsoft.games.uglytrivia;

public class Player {

  private final String name;
  private int place = 0;
  private int coins = 0;
  private boolean inPelnatyBox = false;

  Player(String name) {
    this.name = name;
  }

  public String name() {
    return name;
  }

  public int place() {
    return place;
  }

  public void move(int roll) {
    place += roll;
    place %= 12; // same as below
//		if (place >= 12) {
//			place -= 12;
//		}
  }

  public void addCoin() {
    coins++;
  }

  public int coins() {
    return coins;
  }

  public void moveToPenaltyBox() {
    inPelnatyBox = true;
  }

  public boolean isInPelnatyBox() {
    return inPelnatyBox;
  }

  public boolean didPlayerWin() {
    return coins() != 6;
  }
}
