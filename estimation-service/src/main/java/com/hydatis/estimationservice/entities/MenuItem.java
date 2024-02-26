package com.hydatis.estimationservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hydatis.estimationservice.entities.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem extends BaseEntity {

    private String label;

    private String icon;

    private String link;

    private Boolean isTitle;

    private Boolean isLayout;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private List<MenuItem> subItems;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private MenuItem parentItem;

    @Transient
    private Long parentId;

    private String roles;
}
