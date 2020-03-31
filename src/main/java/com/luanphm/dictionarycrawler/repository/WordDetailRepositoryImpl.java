package com.luanphm.dictionarycrawler.repository;

import com.luanphm.dictionarycrawler.constant.CommonConstants;
import com.luanphm.dictionarycrawler.entity.DefinitionDetail;
import com.luanphm.dictionarycrawler.entity.Pronunciation;
import com.luanphm.dictionarycrawler.entity.WordDetail;
import com.luanphm.dictionarycrawler.utils.Navigator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WordDetailRepositoryImpl implements WordDetailRepository {

    private Navigator navigator;

    @Value("${chrome.driver}")
    private String driver;

    public WordDetailRepositoryImpl() {
    }

    @PostConstruct
    public void init() {
        navigator = new Navigator(driver);
    }

    @Override
    public WordDetail getWord(String word) {

        WebDriver driver = navigator.getDriver();
        navigator.navigateTo(CommonConstants.BASE_URL + word);

        if (driver.getCurrentUrl().equals(CommonConstants.WORD_NOT_FOUND_URL)) return null;

        String ukPhonetic = navigator.getText(CommonConstants.UK_PHONETIC);
        String usPhonetic = navigator.getText(CommonConstants.US_PHONETIC);

        List<WebElement> definitionBlocks = driver.findElements(By.cssSelector(CommonConstants.DEFINITION_BLOCKS));
        List<DefinitionDetail> definitionDetails = new ArrayList<>();
        for (WebElement definitionBlock : definitionBlocks) {

            String partOfSpeech = navigator.getText(CommonConstants.PART_OF_SPEECH);
            String level = navigator.getText(CommonConstants.LEVEL, definitionBlock);
            String definition =  navigator.getText(CommonConstants.DEFINITION, definitionBlock);
            List<String> examples = navigator.getTexts(CommonConstants.EXAMPLES, definitionBlock);
            List<String> synonyms = navigator.getAttributes(CommonConstants.SYNONYMS, "title", definitionBlock);

            DefinitionDetail definitionDetail = DefinitionDetail.builder()
                    .definition(definition)
                    .partOfSpeech(partOfSpeech)
                    .level(level)
                    .synonyms(synonyms)
                    .examples(examples)
                    .build();

            definitionDetails.add(definitionDetail);
        }

        WordDetail wordDetail = WordDetail.builder()
                .word(word)
                .definitionDetails(definitionDetails)
                .pronunciation(Pronunciation.builder().usPhonetic(usPhonetic).ukPhonetic(ukPhonetic).build())
                .build();
        return wordDetail;
    }
}
