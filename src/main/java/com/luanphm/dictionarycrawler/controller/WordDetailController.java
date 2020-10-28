package com.luanphm.dictionarycrawler.controller;

import com.luanphm.dictionarycrawler.entity.WordDetail;
import com.luanphm.dictionarycrawler.repository.WordDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequestMapping("word")
public class WordDetailController {

    @Autowired
    WordDetailRepository wordDetailRepository;

    @GetMapping("{word}")
    public WordDetail getWord(@PathVariable String word) throws MalformedURLException {
        return wordDetailRepository.getWord(word);
    }

}
