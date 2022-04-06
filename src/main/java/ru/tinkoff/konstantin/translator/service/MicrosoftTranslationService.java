package ru.tinkoff.konstantin.translator.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.model.Translation;
import ru.tinkoff.konstantin.translator.model.TranslationWrapper;
import ru.tinkoff.konstantin.translator.utils.TextHandler;

import java.util.List;

@Service
public class MicrosoftTranslationService implements TranslationService {

    @Value("${api.microsoft.uri}")
    private String uriFormat;
    @Value("${header.api.microsoft.key.name}")
    private String apiKeyHeaderName;
    @Value("${header.api.microsoft.key.value}")
    private String apiKeyValue;

    @Override
    public List<Translation> translate(Text text, String from, String to)
            throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(apiKeyHeaderName, apiKeyValue);
        TextHandler textHandler = new TextHandler();
        HttpEntity<List<Text>> request =
                new HttpEntity<>(textHandler.parseToWords(text), headers);
        String body = (new RestTemplate()).postForObject(
                String.format(uriFormat, from, to), request, String.class);
        Gson gson = new Gson();
        TranslationWrapper[] translationWords =
                gson.fromJson(body, TranslationWrapper[].class);
        return textHandler.concat(translationWords);
    }
}
