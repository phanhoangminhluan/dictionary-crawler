package com.luanphm.dictionarycrawler.repository;

import com.luanphm.dictionarycrawler.entity.WordDetail;

import java.net.MalformedURLException;

public interface WordDetailRepository {

    WordDetail getWord(String word) throws MalformedURLException;
}
