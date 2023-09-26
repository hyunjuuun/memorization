package com.hyunjuuun.memorization.web.dto.response;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import com.hyunjuuun.memorization.web.dto.GlossaryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class GlossariesResponseDto {

    private List<GlossaryDto> glossaries;

    public static GlossariesResponseDto of(List<Glossary> glossaries) {
        return new GlossariesResponseDto(
                glossaries.stream().map(GlossaryDto::of).collect(Collectors.toList())
        );
    }

}