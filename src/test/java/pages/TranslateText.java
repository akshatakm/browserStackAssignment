package pages;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TranslateText {

    public String translateTextToEnglish(String spanishText) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google-translate113.p.rapidapi.com/api/v1/translator/text"))
                .header("x-rapidapi-key", "1d25c797c8mshd4011ca90048f49p1a1e19jsn54b722f67f2b")
                .header("x-rapidapi-host", "google-translate113.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"from\":\"es\",\"to\":\"en\",\"text\":\""+spanishText+"\"}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String[] translatedText = response.body().split("[\"]");
       return translatedText[3];
    }

}
