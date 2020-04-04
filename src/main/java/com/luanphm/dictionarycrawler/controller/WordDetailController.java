package com.luanphm.dictionarycrawler.controller;

import com.luanphm.dictionarycrawler.entity.WordDetail;
import com.luanphm.dictionarycrawler.repository.WordDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("word")
public class WordDetailController {

    @Autowired
    WordDetailRepository wordDetailRepository;

    @Autowired
    WordDetailRepository wordDetailRepository1;

    @Autowired
    WordDetailRepository wordDetailRepository2;

    @Autowired
    WordDetailRepository wordDetailRepository3;

    @Autowired
    WordDetailRepository wordDetailRepository4;

    @GetMapping("type-1/{word}")
    public WordDetail getWord1(@PathVariable String word) {
        WordDetail wordDetail = WordDetail.builder().word("please stop now").build();
        try {

            wordDetail = wordDetailRepository1.getWord(word);
        } catch (Exception e) {
            e.printStackTrace();
            return wordDetail;
        }
        return wordDetail;
    }
    @GetMapping("type-2/{word}")
    public WordDetail getWord2(@PathVariable String word) {
        WordDetail wordDetail = WordDetail.builder().word("please stop now").build();
        try {

            wordDetail = wordDetailRepository2.getWord(word);
        } catch (Exception e) {
            e.printStackTrace();
            return wordDetail;
        }
        return wordDetail;
    }
    @GetMapping("type-3/{word}")
    public WordDetail getWord3(@PathVariable String word) {
        WordDetail wordDetail = WordDetail.builder().word("please stop now").build();
        try {

            wordDetail = wordDetailRepository3.getWord(word);
        } catch (Exception e) {
            e.printStackTrace();
            return wordDetail;
        }
        return wordDetail;
    }

    @GetMapping("type-4/{word}")
    public WordDetail getWord4(@PathVariable String word) {
        WordDetail wordDetail = WordDetail.builder().word("please stop now").build();
        try {

            wordDetail = wordDetailRepository4.getWord(word);
        } catch (Exception e) {
            e.printStackTrace();
            return wordDetail;
        }
        return wordDetail;
    }

    @GetMapping("{word}")
    public WordDetail getWord(@PathVariable String word) {
        WordDetail wordDetail = WordDetail.builder().word("please stop now").build();
        try {

            wordDetail = wordDetailRepository.getWord(word);
        } catch (Exception e) {
            e.printStackTrace();
            return wordDetail;
        }
        return wordDetail;
    }
}
