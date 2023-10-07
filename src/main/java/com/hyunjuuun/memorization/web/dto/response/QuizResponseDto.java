package com.hyunjuuun.memorization.web.dto.response;

import com.hyunjuuun.memorization.web.dto.QuizDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QuizResponseDto {

    Integer total;
    List<QuizDto> quizzes = new ArrayList<>();

    public QuizResponseDto(Integer totalQuizNumber) {
        this.total = totalQuizNumber;
    }
}