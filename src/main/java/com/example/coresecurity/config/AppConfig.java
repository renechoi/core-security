// package com.example.coresecurity.config;
//
// import org.springframework.boot.autoconfigure.AutoConfigureBefore;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//
// import com.example.coresecurity.repository.AccessIpRepository;
// import com.example.coresecurity.repository.ResourcesRepository;
// import com.example.coresecurity.security.configs.MethodSecurityConfig;
// import com.example.coresecurity.service.RoleHierarchyService;
// import com.example.coresecurity.service.SecurityResourceService;
//
// @Configuration
// @AutoConfigureBefore({MethodSecurityConfig.class})
// public class AppConfig {
//
//     @Bean
//     public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository, RoleHierarchyImpl roleHierarchy, RoleHierarchyService roleHierarchyService, AccessIpRepository accessIpRepository) {
//         SecurityResourceService SecurityResourceService = new SecurityResourceService(resourcesRepository, roleHierarchy, roleHierarchyService, accessIpRepository);
//         return SecurityResourceService;
//     }
// }