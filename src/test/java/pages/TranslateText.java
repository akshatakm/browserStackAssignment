package pages;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TranslateText {

    public String translateTextToEnglish(String spanishText) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google-api31.p.rapidapi.com/gtranslate"))
                .header("x-rapidapi-key", "1d25c797c8mshd4011ca90048f49p1a1e19jsn54b722f67f2b")
                .header("x-rapidapi-host", "google-api31.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"text\":\""+spanishText+"\",\"to\":\"en\",\"from_lang\":\"es\"}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.body());
        String[] translatedText = response.body().split("[\"]");
       return translatedText[3];




    }

}
