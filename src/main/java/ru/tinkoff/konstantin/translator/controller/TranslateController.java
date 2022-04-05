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
import ru.tinkoff.konstantin.translator.service.TranslateService;

@RestController
public class TranslateController {

    private TranslateService translateService;

    public TranslateController(
            @Autowired TranslateService translationService) {
        this.translateService = translationService;
    }

    @PostMapping("/translate")
    ResponseEntity newTranslate(@RequestBody Text text,
                                @RequestParam String from,
                                @RequestParam String to) {
        ResponseEntity responseEntity;
        try {
            responseEntity = ResponseEntity.ok(translateService.
                    translate(text, from, to));
        } catch (HttpClientErrorException.BadRequest e) {
            responseEntity = ResponseEntity.badRequest().
                    contentType(MediaType.APPLICATION_JSON).
                    body(e.getResponseBodyAsString());
        }
        return responseEntity;
    }

}
