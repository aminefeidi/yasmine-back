package com.hydatis.projectservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hello")
public class Hello {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String vval;

}