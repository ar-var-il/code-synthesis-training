package com.encora.code_synthesis_training.repository;

import com.encora.code_synthesis_training.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}