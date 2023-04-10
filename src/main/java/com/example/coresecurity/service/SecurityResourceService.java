package com.example.coresecurity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.coresecurity.domain.entity.Resources;
import com.example.coresecurity.repository.ResourcesRepository;

@Slf4j
public class SecurityResourceService {

    private ResourcesRepository resourcesRepository;

    public SecurityResourceService(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resources = resourcesRepository.findAllResources();

        resources.forEach(resource ->   // 각각의 리소스에 대해서
                {
                    List<ConfigAttribute> configAttributeList = new ArrayList<>();
                    resource.getRoleSet().forEach(role -> {
                        configAttributeList.add(new SecurityConfig(role.getRoleName()));
                        result.put(new AntPathRequestMatcher(resource.getResourceName()), configAttributeList); // 리소스 하나 당 1대 다 형식의 권한들을 담아서
                    });
                }
        );
        log.debug("cache test");
        return result;
    }
}