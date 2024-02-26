package com.hydatis.testservice.entities;

import com.hydatis.testservice.entities.audit.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "test_entity")
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity extends BaseEntity {
    public String val;
}