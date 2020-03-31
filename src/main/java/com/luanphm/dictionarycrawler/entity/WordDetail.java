package com.luanphm.dictionarycrawler.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordDetail{

    private String word;
    private List<DefinitionDetail> definitionDetails = new ArrayList<>();
    private Pronunciation pronunciation;


}