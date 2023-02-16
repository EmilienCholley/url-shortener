package com.cholley.urlshortener.service;

import com.cholley.urlshortener.entity.Url;

import java.util.Optional;

public interface UrlService {
    Optional<Url> shortenUrl(String newUrl);

    Optional<Url> getUrl(String shortenUrl);
}
