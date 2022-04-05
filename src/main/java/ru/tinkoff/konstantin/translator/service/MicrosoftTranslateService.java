package ru.tinkoff.konstantin.translator.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.utils.Parser;

import java.util.List;

@Service
public class MicrosoftTranslateService implements TranslateService {

    private static final String URL_FORMAT = "https://microsoft-translator-text.p.rapidapi.com/translate?from=%s&to=%s&api-version=3.0";

    @Override
    public List translate(Text text, String from, String to) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Rapidapi-Key", "732dc95b60msh066737340605a17p18487fjsndd2c543b5933");
        HttpEntity<List<Text>> request =
                new HttpEntity<>(Parser.parse(text), headers);
        return (new RestTemplate()).postForObject(
                String.format(URL_FORMAT, from, to), request, List.class);
    }
}
