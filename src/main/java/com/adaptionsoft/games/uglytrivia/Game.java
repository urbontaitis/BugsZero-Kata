package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Game {

  private final List<Player> players = new ArrayList<>();
  private final Questions questions = new Questions();

  int currentPlayer = 0;
  boolean isGettingOutOfPenaltyBox;

  public void add(String playerName) {
    players.add(new Player(playerName));

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
  }

  public void roll(int roll) {
    System.out.println(currentPlayer().name() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (currentPlayer().isInPelnatyBox()) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        System.out.println(currentPlayer().name() + " is getting out of the penalty box");
        currentPlayer().move(roll);
        System.out.println(currentPlayer().name()
            + "'s new location is "
            + currentPlayer().place());
        System.out.println(
            "The category is " + questions.currentCategory(currentPlayer().place()).label());
        askQuestion();
      } else {
        System.out.println(currentPlayer().name() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }

    } else {
      currentPlayer().move(roll);
      System.out.println(currentPlayer().name() + "'s new location is " + currentPlayer().place());
      System.out
          .println("The category is " + questions.currentCategory(currentPlayer().place()).label());
      askQuestion();    }
  }

  private void askQuestion() {
    System.out.println(questions.extractNextQuestion(currentPlayer().place()));
  }

  private Player currentPlayer() {
    return players.get(currentPlayer);
  }

  public boolean wasCorrectlyAnswered() {
    boolean result = extracted();
    moveToTheNextPlayer();
    return result;
  }

  private boolean extracted() {
    if (currentPlayer().isInPelnatyBox()) {
      if (isGettingOutOfPenaltyBox) {
        System.out.println("Answer was correct!!!!");
        currentPlayer().addCoin();
        System.out.println(
            currentPlayer().name() + " now has " + currentPlayer().coins() + " Gold Coins.");

        return currentPlayer().didPlayerWin();
      } else {
        return true;
      }
    } else {
      System.out.println("Answer was corrent!!!!");
      currentPlayer().addCoin();
      System.out.println(currentPlayer().name()
          + " now has "
          + currentPlayer().coins()
          + " Gold Coins.");

      return currentPlayer().didPlayerWin();
    }
  }

  private void moveToTheNextPlayer() {
    currentPlayer++;
    if (currentPlayer == players.size()) {
      currentPlayer = 0;
    }
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(currentPlayer().name() + " was sent to the penalty box");
    currentPlayer().moveToPenaltyBox();

    moveToTheNextPlayer();
    return true;
  }

}
