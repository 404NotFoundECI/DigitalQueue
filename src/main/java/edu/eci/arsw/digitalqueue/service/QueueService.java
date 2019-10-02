package edu.eci.arsw.digitalqueue.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.eci.arsw.digitalqueue.model.Queue;

@Service
public interface QueueService {

    void newQueue(Queue newQueue);

    Queue findByName(String name);

    Optional<Queue> findById(Long id);

    void updateByName(String name);

    void deleteByName(String name);

}
