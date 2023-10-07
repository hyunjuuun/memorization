package com.hyunjuuun.memorization.service.quiz;

import com.hyunjuuun.memorization.domain.term.Term;
import com.hyunjuuun.memorization.domain.term.TermRepository;
import com.hyunjuuun.memorization.enums.QuizType;
import com.hyunjuuun.memorization.web.dto.QuizDto;
import com.hyunjuuun.memorization.web.dto.response.QuizResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * glossaryId를 이용해 문제를 출제한다
 */

@Service
@RequiredArgsConstructor
public class QuizService {

    private final TermRepository termRepository;
    private static final int QUIZ_NUMBER = 2;
    private static final String[] TYPES_OF_TERM = {"word", "description"};

    public QuizResponseDto getQuizzes(Long glossaryId) {
        List<Term> terms = termRepository.findByGlossaryId(glossaryId);
        QuizResponseDto quizResponseDto = new QuizResponseDto(QUIZ_NUMBER);

        Random random = new Random();
        for (int i = 0; i < QUIZ_NUMBER; i++) {
            int size = terms.size();
            int termRandomIndex = random.nextInt(size);
            Term termForQuiz = terms.get(termRandomIndex);

            terms.remove(termRandomIndex); // 문제 중복출제 방지

            int typeRandomIndex = random.nextInt(2);
            String typeForQuiz = QuizType.getNameByIdx(typeRandomIndex);
            quizResponseDto.getQuizzes().add(new QuizDto(termForQuiz.getId(), typeForQuiz, extractQuizText(termForQuiz, typeRandomIndex)));
        }

        return quizResponseDto;
    }

    private String extractQuizText(Term termForQuiz, int typeIndex) {
        String quizText;
        if (typeIndex == 0) {
            quizText = termForQuiz.getWord();
        } else {
            quizText = termForQuiz.getDescription();
        }
        return quizText;
    }
}
