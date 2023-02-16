package com.cholley.urlshortener.controller;

import com.cholley.urlshortener.entity.ShortenedUrl;
import com.cholley.urlshortener.entity.Url;
import com.cholley.urlshortener.service.ShortenedUrlService;
import com.cholley.urlshortener.service.UrlService;
import com.cholley.urlshortener.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping(value = "/url-Shortener")
@CrossOrigin
public class UrlShortenerController {

    @Autowired
    UrlService urlService;
    @Autowired
    ShortenedUrlService shortenedUrlService;

    /**
     * api to get the original url associated with the shortened url in param
     * @param shortenedUrl associated with an url
     * @return the original url assciated with the shortened url
     */
    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortenedUrl) {
        if (Validator.isValidShortenedUrl(shortenedUrl)) {
            Optional<Url> url = urlService.getUrl(shortenedUrl);
            return url.map(value -> ResponseEntity.ok().body(value.getUrlValue())).orElseGet(() -> ResponseEntity.noContent().build());
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * api to creat a shortened url associated with the url in param
     * @param url use to creat a shortened url
     * @return the new shortened url associated with the url
     */
    @PostMapping
    public ResponseEntity<String> shortenUrl(@RequestParam(value = "url", defaultValue = "") String url) {
        if (Validator.isValidURL(url)) {
            if (shortenedUrlService.exists(url)) {
                Optional<ShortenedUrl> shortenUrl = shortenedUrlService.getShortenedUrl(url);
                return shortenUrl.map(
                        shortenedUrl -> ResponseEntity.ok().body(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/url-Shortener/" + shortenedUrl.getShortenedUrlValue()))
                        .orElseGet(() -> ResponseEntity.noContent().build());
            } else {
                Optional<Url> urlObject = urlService.shortenUrl(url);
                return urlObject.map(
                        value -> ResponseEntity.ok().body(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/url-shortener/" + value.getShortenedUrl()))
                        .orElseGet(() -> ResponseEntity.noContent().build());
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
