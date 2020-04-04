package com.luanphm.dictionarycrawler.repository;

import com.luanphm.dictionarycrawler.entity.WordDetail;

public interface WordDetailRepository {

    WordDetail getWord(String word) throws Exception;
}
