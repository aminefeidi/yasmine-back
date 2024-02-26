package com.hydatis.estimationservice.repositories;

import com.hydatis.estimationservice.entities.MenuItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    @Query("SELECT mi FROM MenuItem mi LEFT JOIN FETCH mi.subItems")
    List<MenuItem> findAllWithSubItems();
}
