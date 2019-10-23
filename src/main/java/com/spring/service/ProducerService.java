package com.spring.service;

import com.spring.model.Producer;

import java.util.UUID;

public interface ProducerService {
    Producer findByName(String name);

    Producer save(Producer producer);

    Producer findById(UUID id);

    void deleteById(UUID id);
}
