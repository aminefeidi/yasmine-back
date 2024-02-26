package com.hydatis.estimationservice.controllers;

import com.hydatis.estimationservice.entities.Estimation;
import com.hydatis.estimationservice.entities.MenuItem;
import com.hydatis.estimationservice.repositories.EstimationRepository;
import com.hydatis.estimationservice.repositories.MenuItemRepository;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("estimates")
public class EstimateController {
    private final EstimationRepository repository;
    private final MenuItemRepository menuItemRepository;

    public EstimateController(EstimationRepository repository, MenuItemRepository menuItemRepository) {
        this.repository = repository;
        this.menuItemRepository = menuItemRepository;
    }

    @GetMapping("/")
    List<Estimation> all() {
        return (List<Estimation>) repository.findAll();
    }

    @PostMapping("/")
    Estimation newEstimation(Estimation newEstimation, @AuthenticationPrincipal Jwt jwt) {
        newEstimation.setUserId(jwt.getClaim("sub"));
        return repository.save(newEstimation);
    }

    @GetMapping("/estimation/{userStoryId}")
    String calculateEstimationMedian(@PathVariable Long userStoryId) {
        // Get all estimations from the repository
        List<String> allEstimations = Streamable.of(repository.findAllByUserStoryId(userStoryId)).stream()
                .map(Estimation::getValue) // Assuming the class name is Estimation
                .toList();

        // Count the occurrences of each estimation value
        Map<String, Long> occurrenceMap = allEstimations.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // log the occurrence map
        occurrenceMap.forEach((key, value) -> System.out.println(key + " : " + value));

        // sort the occurrence map by value
        List<Map.Entry<String, Long>> sortedMap = occurrenceMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();

        // Calculate the median index
        int totalCount = allEstimations.size();
        int cumulativeCount = 0;
        String medianValue = null;
        for (Map.Entry<String, Long> entry : sortedMap) {
            cumulativeCount += entry.getValue().intValue();
            if (cumulativeCount >= (totalCount + 1) / 2) {
                medianValue = entry.getKey();
                break;
            }
        }

        return medianValue;
    }

    @GetMapping("/estimation/stats/byUserStory")
    Map<Long, Long> getEstimationStatsByUserStory() {
        return Streamable.of(repository.findAll()).stream()
                .collect(Collectors.groupingBy(Estimation::getUserStoryId, Collectors.counting()));
    }

    @GetMapping("/estimation/stats/byProject")
    Map<Long, Long> getEstimationStatsByProject() {
        return Streamable.of(repository.findAll()).stream()
                .collect(Collectors.groupingBy(Estimation::getProjectId, Collectors.counting()));
    }

    @GetMapping("/estimation/stats/bySession")
    Map<Long, Long> getEstimationStatsBySession() {
        return Streamable.of(repository.findAll()).stream()
                .collect(Collectors.groupingBy(Estimation::getSessionId, Collectors.counting()));
    }

    @GetMapping("/menu")
    List<MenuItem> getMenu() {
        return menuItemRepository.findAllWithSubItems();
    }

    @PutMapping("/menu/{id}")
    MenuItem updateMenuItem(@RequestBody MenuItem menuItem, @PathVariable Long id) {
        menuItem.setId(id);
        return menuItemRepository.save(menuItem);
    }
}

