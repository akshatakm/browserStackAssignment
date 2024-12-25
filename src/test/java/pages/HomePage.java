package pages;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomePage extends BasePage{
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    TranslateText translateText = new TranslateText();
    @FindBy(id = "edition_head")
    WebElement languageDropdown;

    @FindBy(tagName = "article")
    List<WebElement> articles;

    @FindBy(linkText = "Opinión")
    WebElement opinionSection;

    @FindBy(tagName = "h2")
    List<WebElement> headerList;

    @FindBy(tagName = "p")
    List<WebElement> contentList;

    String language;

    @FindBys({
            @FindBy(tagName = "article"),
            @FindBy(tagName = "figure"),
            @FindBy(tagName = "img")
    }
    )
    List<WebElement> image;

    @FindAll({
            @FindBy(id = "didomi-notice-agree-button"),
            @FindBy(className = "pmConsentWall-button")
    })
    WebElement cookieConsentBtn;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean validatePageInSpanish(){
        if(isElementVisible(cookieConsentBtn)){
            System.out.println("consent block visible");
            cookieConsentBtn.click();
        }
        language = driver.findElement(By.xpath("//html")).getAttribute("lang");
        if(language.contains("ES") || language.contains("es")){
            System.out.println("Website is in spanish");
            return true;
        }else{
            System.out.println("Website is not in spanich");
            return false;
        }
    }

    public boolean printArticlesinOpinionSection() {
        if(isElementClickable(opinionSection)) {
            opinionSection.click();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            int i;
            System.out.println("Printing articles:");
            for (i = 0; i < 5; i++) {
                int articleNo = i + 1;
                System.out.println("Article no " + articleNo);
                System.out.println(headerList.get(i).getText());
                System.out.println(contentList.get(i).getText());
            }
            if(i>0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }

    }

    public void saveImage() throws Exception {
        for(int i=0; i < image.size(); i++) {
            URL imgUrl = new URL(image.get(i).getAttribute("src"));
            BufferedImage saveImage = ImageIO.read(imgUrl);
            String fileName = "cover_img"+i+".png";
            ImageIO.write(saveImage, "png", new File("/Users/akshathak/Documents/"+fileName));
        }
    }

    public void translateHeadersToEnglish(){
        String[] translatedHeaders = new String[5];
        String[] words = new String[0];
        for(int i=0; i<5;i++){
            translatedHeaders[i] = translateText.translateTextToEnglish(headerList.get(i).getText());
        }
        for(int i=0; i<5; i++){
            words = ArrayUtils.addAll(words,translatedHeaders[i].split("\\s"));
        }
        Map<String, Integer> wordMap = new HashMap<>();
        for(String word : words){
            if (wordMap.get(word) != null) {
                wordMap.put(word, wordMap.get(word) + 1);
            }
            // if the word came once then occurrence is 1.
            else {
                wordMap.put(word, 1);
            }
        }
        Set<String> wordSet = wordMap.keySet();
        int repeatedWordsCounter = 0;
        for(String word : wordSet){
            if(wordMap.get(word)>2){
                System.out.println(word+" repeated "+wordMap.get(word)+" times");
                repeatedWordsCounter++;
            }
        }
        if(repeatedWordsCounter == 0){
            System.out.println("There are no words repeating more than twice in the first five headers");
        }
    }
}

