package ru.tinkoff.konstantin.translator.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tinkoff.konstantin.translator.entity.RequestEntity;

public interface RequestRepository extends CrudRepository<RequestEntity, Long> {

}
