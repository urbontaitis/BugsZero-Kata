package com.adaptionsoft.games.uglytrivia;

enum QuestionCategory {
  POP("Pop"),
  SCIENCE("Science"),
  SPORTS("Sports"),
  ROCK("Rock");

  private final String label;

  QuestionCategory(String label) {
    this.label = label;
  }

  String label() {
    return label;
  }
}
