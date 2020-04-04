package com.luanphm.dictionarycrawler.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Navigator implements Cloneable{

    protected static WebDriver driver;

    public Navigator() {

        ChromeDriverManager.getInstance(WebDriver.class);
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
    }
    public Navigator(String chromeDriverUrl) {
        System.setProperty("webdriver.chrome.driver", chromeDriverUrl);

        ChromeDriverManager.getInstance(WebDriver.class);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public void navigateTo(String url) throws ConnectException {
        driver.get(url);

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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
