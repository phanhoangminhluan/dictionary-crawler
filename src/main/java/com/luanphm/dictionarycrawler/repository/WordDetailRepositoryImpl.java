package com.luanphm.dictionarycrawler.repository;

import com.luanphm.dictionarycrawler.constant.CommonConstants;
import com.luanphm.dictionarycrawler.entity.DefinitionDetail;
import com.luanphm.dictionarycrawler.entity.Pronunciation;
import com.luanphm.dictionarycrawler.entity.WordDetail;
import com.luanphm.dictionarycrawler.utils.Navigator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WordDetailRepositoryImpl implements WordDetailRepository {

    private Navigator navigator = null;

    private ChromeOptions chromeOptions;

    private WebDriver webDriver;

    @Value("${chrome-url}")
    private String remoteChromeUrl;

    public WordDetailRepositoryImpl() {
    }

    @PostConstruct
    public void init() throws MalformedURLException {
        this.chromeOptions = new ChromeOptions().setHeadless(true);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setBrowserName("chrome");
        chromeOptions.addArguments(
                "--verbose",
                "--headless",
                "--disable-web-security",
                "--ignore-certificate-errors",
                "--allow-running-insecure-content",
                "--allow-insecure-localhost",
                "--no-sandbox",
                "--disable-gpu"
        );
    }

    @PreDestroy
    public void destroy() {
        this.webDriver.quit();
    }

    @Override
    public WordDetail getWord(String word) throws MalformedURLException {
        if (navigator == null) {
            System.out.println("URL url = new URL(remoteChromeUrl);");
            URL url = new URL(remoteChromeUrl);
            System.out.println("this.webDriver = new RemoteWebDriver(url, chromeOptions);");
            this.webDriver = new RemoteWebDriver(url, chromeOptions);
            System.out.println("navigator = new Navigator(webDriver);");
            navigator = new Navigator(webDriver);
        }

        this.webDriver.get(CommonConstants.BASE_URL + word);

        if (this.webDriver.getCurrentUrl().equals(CommonConstants.WORD_NOT_FOUND_URL)) return null;

        String ukPhonetic = navigator.getText(CommonConstants.UK_PHONETIC);
        String usPhonetic = navigator.getText(CommonConstants.US_PHONETIC);

        List<WebElement> definitionBlocks = this.webDriver.findElements(By.cssSelector(CommonConstants.DEFINITION_BLOCKS));
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
//        this.webDriver.quit();
        WordDetail wordDetail = WordDetail.builder()
                .word(word)
                .definitionDetails(definitionDetails)
                .pronunciation(Pronunciation.builder().usPhonetic(usPhonetic).ukPhonetic(ukPhonetic).build())
                .build();
        return wordDetail;
    }
}
