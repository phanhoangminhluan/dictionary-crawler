package com.luanphm.dictionarycrawler.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefinitionDetail {
    private String definition;

    private String partOfSpeech;

    private String level;

    private List<String> examples;

    @JsonAlias("derivation")
    private List<String> derivations;

    private List<String> synonyms;
}
