package com.cholley.urlshortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("ShortenUrl")
@Data
@AllArgsConstructor
public class ShortenedUrl {
    @Id
    private String url;
    private String shortenedUrlValue;
}
