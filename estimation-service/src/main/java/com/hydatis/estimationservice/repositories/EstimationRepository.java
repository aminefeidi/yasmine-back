package com.hydatis.estimationservice.repositories;

import com.hydatis.estimationservice.entities.Estimation;
import org.springframework.data.repository.CrudRepository;

public interface EstimationRepository extends CrudRepository<Estimation, Long> {
    Iterable<Estimation> findAllByUserStoryId(Long userStoryId);
}
