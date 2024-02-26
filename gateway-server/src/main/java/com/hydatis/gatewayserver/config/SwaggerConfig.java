package com.hydatis.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private final RouteDefinitionLocator locator;

    public SwaggerConfig(RouteDefinitionLocator locator) {
        this.locator = locator;
    }

//    @Bean
//    public List<GroupedOpenApi> apis() {
//        List<GroupedOpenApi> groups = new ArrayList<>();
//        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//        assert definitions != null;
//        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
//            String name = routeDefinition.getId().replaceAll("-service", "");
//            groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
//        });
//        return groups;
//    }
}