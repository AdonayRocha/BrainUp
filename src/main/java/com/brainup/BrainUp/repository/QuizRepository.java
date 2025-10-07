package com.brainup.BrainUp.repository;

import com.brainup.BrainUp.entity.Alternative;
import com.brainup.BrainUp.entity.Question;
import com.brainup.BrainUp.entity.Quiz;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Getter
public class QuizRepository {
    private final Quiz quiz;

    public QuizRepository() {
        List<Question> questions = List.of(
                createQuestion("Qual palavra-chave é usada para definir uma classe em Java?", List.of("class", "struct", "define", "object"), 0),
                createQuestion("Qual tipo de dado armazena números inteiros?", List.of("int", "double", "String", "boolean"), 0),
                createQuestion("Como se declara um método estático?", List.of("static void metodo()", "void static metodo()", "method static void()", "void metodo static()"), 0),
                createQuestion("Qual é o operador de atribuição em Java?", List.of("=", ":=", "==", "<-"), 0),
                createQuestion("Qual estrutura de controle repete um bloco de código enquanto uma condição é verdadeira?", List.of("while", "if", "switch", "break"), 0)
        );
        this.quiz = new Quiz();
        this.quiz.setId(UUID.randomUUID());
        this.quiz.setTitle("Quiz de Conhecimentos Gerais de Java");
        this.quiz.setDescription("Teste seus conhecimentos sobre programação Java");
        this.quiz.setQuestions(questions);
        this.quiz.setPlayers(new ArrayList<>());
    }

    private Question createQuestion(String questionText, List<String> alternativesText, int correctIndex) {
        Question question = new Question();
        question.setId(UUID.randomUUID());
        question.setText(questionText);
        List<Alternative> alternatives = new ArrayList<>();
        for (int i = 0; i < alternativesText.size(); i++) {
            Alternative alt = new Alternative();
            alt.setId(UUID.randomUUID());
            alt.setText(alternativesText.get(i));
            alt.setCorrect(i == correctIndex);
            alternatives.add(alt);
        }
        question.setAlternatives(alternatives);
        return question;
    }
}
