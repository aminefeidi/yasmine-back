package com.hydatis.testservice;

import com.hydatis.testservice.entities.TestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final TestEntityRepository repository;

    @GetMapping
    public TestRec getUser(@AuthenticationPrincipal Jwt jwt) {
        return new TestRec(1,
                jwt.getClaimAsString(StandardClaimNames.PREFERRED_USERNAME),
                jwt.getClaim(StandardClaimNames.SUB),
                jwt.getClaim(StandardClaimNames.EMAIL));
    }

    @PostMapping
    TestEntity addNew(@RequestBody TestEntity entity) {
        return repository.save(entity);
    }

    @PutMapping("/{id}")
    TestEntity update(@RequestParam long id, @RequestBody TestEntity entity) {
        var current = repository.findById(id).orElse(null);
        assert current != null;
        current.setVal(entity.val);
        return repository.save(current);
    }

    public record TestRec(long id, String username, String keycloakId, String email) {
    }

}
