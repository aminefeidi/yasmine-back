package com.hydatis.testservice;

import com.hydatis.testservice.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
}