package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Questions {

  private final Map<QuestionCategory, List<String>> questions = new HashMap<>();

  Questions() {
    Stream.of(QuestionCategory.values())
        .forEach(questionCategory -> questions.put(questionCategory, new ArrayList<>()));

    for (int i = 0; i < 50; i++) {
      questions.get(QuestionCategory.POP).add("Pop Question " + i);
      questions.get(QuestionCategory.SCIENCE).add("Science Question " + i);
      questions.get(QuestionCategory.SPORTS).add("Sports Question " + i);
      questions.get(QuestionCategory.ROCK).add("Rock Question " + i);

    }
  }

  public String extractNextQuestion(int index) {
    QuestionCategory questionCategory = currentCategory(index);
    return questions.get(questionCategory).remove(0);
  }

  public QuestionCategory currentCategory(int index) {
    int modulo = index % 4;
    return switch (modulo) {
      case 0 -> QuestionCategory.POP;
      case 1 -> QuestionCategory.SCIENCE;
      case 2 -> QuestionCategory.SPORTS;
      case 3 -> QuestionCategory.ROCK;
      default -> throw new IllegalStateException("Unexpected value: " + modulo);
    };
  }
}
