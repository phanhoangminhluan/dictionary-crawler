package com.luanphm.dictionarycrawler.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pronunciation {
    private String ukPhonetic;
    private String usPhonetic;
}
