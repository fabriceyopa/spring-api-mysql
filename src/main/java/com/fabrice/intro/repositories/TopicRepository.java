package com.fabrice.intro.repositories;

import com.fabrice.intro.models.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TopicRepository extends CrudRepository<Topic, Integer> {}
