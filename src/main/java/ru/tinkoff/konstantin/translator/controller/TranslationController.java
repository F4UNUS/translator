package ru.tinkoff.konstantin.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.service.TranslationService;

@RestController
public class TranslationController {

    private TranslationService translationService;

    public TranslationController(
            @Autowired TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("/translate")
    ResponseEntity newTranslate(@RequestBody Text text,
                                @RequestParam String from,
                                @RequestParam String to) {
        ResponseEntity responseEntity;
        try {
            responseEntity = ResponseEntity.ok(translationService.
                    translate(text, from, to));
        } catch (HttpClientErrorException e) {
            responseEntity = ResponseEntity.
                    status(e.getStatusCode()).
                    contentType(MediaType.APPLICATION_JSON).
                    body(e.getResponseBodyAsString());
        }
        return responseEntity;
    }

}
