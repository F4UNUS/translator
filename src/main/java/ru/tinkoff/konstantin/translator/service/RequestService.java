package ru.tinkoff.konstantin.translator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.konstantin.translator.entity.RequestEntity;
import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.repository.RequestRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class RequestService {
    private RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public RequestEntity create(Text input, String sourceLanguage, String targetLanguages, String output) {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setInput(input.getText());
        requestEntity.setSourceLanguage(sourceLanguage);
        requestEntity.setTargetLanguages(targetLanguages);
        requestEntity.setOutput(output);
        requestEntity.setTime(LocalDateTime.now());
        requestRepository.save(requestEntity);
        return requestEntity;
    }

    private String getClientIp(HttpServletRequest httpServletRequest){
        throw new NotImplementedException();
    }
}
