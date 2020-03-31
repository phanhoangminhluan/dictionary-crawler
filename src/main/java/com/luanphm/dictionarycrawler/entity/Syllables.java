package com.luanphm.dictionarycrawler.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Syllables {
    private int count;
    private List<String> syllableList;
}
