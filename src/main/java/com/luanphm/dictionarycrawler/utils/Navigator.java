package com.luanphm.dictionarycrawler.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Navigator {

    public final WebDriver driver;

    public Navigator() {
        driver = new ChromeDriver();
    }

    public Navigator(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public Navigator(String chromeDriverUrl) {
        System.setProperty("webdriver.chrome.driver", chromeDriverUrl);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public String getText(String cssSelector) {
        String text = "";
        try {
            text = driver.findElement(By.cssSelector(cssSelector)).getText();
        } catch (Exception e) {

        }
        return text;
    }

    public String getText(String cssSelector, WebElement webElement) {
        String text = "";
        try {
            text = webElement.findElement(By.cssSelector(cssSelector)).getText();
        } catch (Exception e) {

        }
        return text;
    }

    public List<String> getTexts(String cssSelector) {
        List<WebElement> webElements = new ArrayList<>();
        try {
            webElements = driver.findElements(By.cssSelector(cssSelector));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getListOfTexts(webElements);
    }

    public List<String> getTexts(String cssSelector, WebElement webElement) {
        List<WebElement> webElements = new ArrayList<>();
        try {
            webElements = webElement.findElements(By.cssSelector(cssSelector));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getListOfTexts(webElements);
    }

    public List<String> getAttributes(String cssSelector, String attribute, WebElement webElement) {
        List<WebElement> webElements = new ArrayList<>();
        try {
            webElements = webElement.findElements(By.cssSelector(cssSelector));

        }catch (Exception e) {
            e.printStackTrace();
        }
        return getListOfAttributes(webElements, attribute);
    }

    private List<String> getListOfAttributes(List<WebElement> webElements, String attribute) {
        List<String> results = new ArrayList<>();
        for (WebElement webElm : webElements) {
            results.add(webElm.getAttribute(attribute));
        }
        return results;
    }
    private List<String> getListOfTexts(List<WebElement> webElements) {
        List<String> results = new ArrayList<>();
        for (WebElement webElm : webElements) {
            results.add(webElm.getText());
        }
        return results;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
