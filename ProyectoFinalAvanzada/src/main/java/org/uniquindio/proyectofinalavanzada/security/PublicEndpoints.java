package org.uniquindio.proyectofinalavanzada.security;

import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
@Component
@Getter
public final class PublicEndpoints {
    private final RequestMatcher[] matchers = new RequestMatcher[]{
            new AntPathRequestMatcher("/public/**"),
            new AntPathRequestMatcher("/login", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/users", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/register", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/auth/register", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/auth/verify", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/api/auth/login", HttpMethod.POST.name()),
    };
}