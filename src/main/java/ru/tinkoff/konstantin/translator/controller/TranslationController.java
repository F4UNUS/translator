package ru.tinkoff.konstantin.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import ru.tinkoff.konstantin.translator.entity.RequestEntity;
import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.model.Translation;
import ru.tinkoff.konstantin.translator.model.TranslationWrapper;
import ru.tinkoff.konstantin.translator.service.RequestService;
import ru.tinkoff.konstantin.translator.service.TextHandlingService;
import ru.tinkoff.konstantin.translator.service.TranslatedWordsService;
import ru.tinkoff.konstantin.translator.service.TranslationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TranslationController {

    private TranslationService translationService;
    private TextHandlingService textHandlerService;
    private RequestService requestService;
    private TranslatedWordsService translatedWordsService;
    private HttpServletRequest httpServletRequest;

    @Autowired
    public TranslationController(TranslationService translationService,
                                 TextHandlingService textHandlerService,
                                 RequestService requestService,
                                 TranslatedWordsService translatedWordsService,
                                 HttpServletRequest httpServletRequest) {
        this.translationService = translationService;
        this.textHandlerService = textHandlerService;
        this.requestService = requestService;
        this.translatedWordsService = translatedWordsService;
        this.httpServletRequest = httpServletRequest;
    }

    @PostMapping("/translate")
    ResponseEntity newTranslate(@RequestBody Text text,
                                @RequestParam String from,
                                @RequestParam String to) {
        ResponseEntity responseEntity;
        try {
            List<Text> sourceWords = textHandlerService.parseToWords(text);
            List<TranslationWrapper> translatedWords =
                    translationService.translate(sourceWords, from, to);
            List<Translation> translations =
                    textHandlerService.concat(translatedWords);
            responseEntity = ResponseEntity.ok(translations);
            RequestEntity requestEntity =
                    requestService.create(
                            text, from, to, translations.toString(),
                            httpServletRequest);
            translatedWordsService.create(
                    sourceWords, from, translatedWords, requestEntity);
        } catch (HttpClientErrorException e) {
            String body = e.getResponseBodyAsString();
            responseEntity = ResponseEntity.
                    status(e.getStatusCode()).
                    contentType(MediaType.APPLICATION_JSON).
                    body(body);
            requestService.create(text, from, to, body, httpServletRequest);
        }
        return responseEntity;
    }

}
