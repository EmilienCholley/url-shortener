package com.cholley.urlshortener.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Url")
@Data
@AllArgsConstructor
public class Url {
    @Id
    private String shortenedUrl;
    private String urlValue;
}
