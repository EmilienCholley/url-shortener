package com.cholley.urlshortener.service;

import com.cholley.urlshortener.entity.ShortenedUrl;

import java.util.Optional;

public interface ShortenedUrlService {
    Optional<ShortenedUrl> shortenUrl(String newID, String newUrl);

    Optional<ShortenedUrl> getShortenedUrl(String url);

    boolean exists(String newUrl);
}
