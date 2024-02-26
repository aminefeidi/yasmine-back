package com.hydatis.estimationservice.entities;

import com.hydatis.estimationservice.entities.audit.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estimation")
@AllArgsConstructor
@NoArgsConstructor
public class Estimation extends BaseEntity {
    public Long projectId;
    public Long userStoryId;
    public Long sessionId;
    public String userId;
    public String value;
}
