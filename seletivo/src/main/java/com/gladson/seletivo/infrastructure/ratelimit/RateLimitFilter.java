package com.gladson.seletivo.infrastructure.ratelimit;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filtro de Rate Limiting por usuário usando Bucket4j (10 requests/minuto).
 */
@Component
public class RateLimitFilter extends OncePerRequestFilter {

    // Map para armazenar bucket de cada usuário
    private final Map<String, Bucket> userBuckets = new ConcurrentHashMap<>();

    // Cria um bucket novo para cada usuário
    private Bucket createNewBucket() {
        Refill refill = Refill.intervally(10, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(10, refill);
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String userId = extractUserId(request);

        // Pega bucket existente ou cria novo
        Bucket bucket = userBuckets.computeIfAbsent(userId, k -> createNewBucket());

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded: 10 requests per minute");
            return;
        }
    }

    /**
     * Extrai o userId do JWT no header Authorization.
     * Substitua a lógica de token conforme seu JWT real.
     */
    private String extractUserId(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            // TODO: decodificar JWT e retornar claim "sub" ou "userId"
            return token; // simplificado para demonstração
        }
        return "anonymous";
    }
}
