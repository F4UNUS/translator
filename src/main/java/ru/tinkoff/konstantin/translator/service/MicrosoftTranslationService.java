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
import ru.tinkoff.konstantin.translator.model.TranslationWrapper;

import java.util.Arrays;
import java.util.List;

@Service
public class MicrosoftTranslationService implements TranslationService {

    @Value("${api.microsoft.uri}")
    private String uriFormat;
    @Value("${header.api.microsoft.key.name}")
    private String apiKeyHeaderName;
    @Value("${header.api.microsoft.key.value}")
    private String apiKeyValue;
    @Value("${api.microsoft.version}")
    private String apiVersion;

    @Override
    public List<TranslationWrapper> translate(List<Text> words, String from, String to)
            throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(apiKeyHeaderName, apiKeyValue);
        HttpEntity<List<Text>> request = new HttpEntity<>(words, headers);
        System.out.println(String.format(uriFormat, from, to, apiVersion));
        String body = (new RestTemplate()).postForObject(
                String.format(uriFormat, from, to, apiVersion), request, String.class);
        return Arrays.asList((new Gson()).fromJson(body, TranslationWrapper[].class));
    }
}
