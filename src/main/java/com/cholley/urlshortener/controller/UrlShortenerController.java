package com.cholley.urlshortener.controller;

import com.cholley.urlshortener.entity.ShortenedUrl;
import com.cholley.urlshortener.entity.Url;
import com.cholley.urlshortener.service.ShortenedUrlService;
import com.cholley.urlshortener.service.UrlService;
import com.cholley.urlshortener.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping(value = "/url-shortener")
@CrossOrigin
public class UrlShortenerController {

    @Autowired
    UrlService urlService;
    @Autowired
    ShortenedUrlService shortenedUrlService;

    Logger logger = LoggerFactory.getLogger(UrlShortenerController.class);


    /**
     * api to get the original url associated with the shortened url in param
     * @param shortenedUrl associated with an url
     * @return the original url assciated with the shortened url
     */
    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortenedUrl) {
        logger.info("GET Request - getOriginalUrl : shortenedUrl=%s".formatted(shortenedUrl));
        if (Validator.isValidShortenedUrl(shortenedUrl)) {
            Optional<Url> url = urlService.getUrl(shortenedUrl);

            return url.map(value -> ResponseEntity.ok().body(value.getUrlValue()))
                    .orElseGet(() -> ResponseEntity.noContent().build());
        }
        logger.error("GET Request - getOriginalUrl : Error: The shortened URL is not valid");

        return ResponseEntity.badRequest().body("Error: The shortened URL is not valid");
    }

    /**
     * api to creat a shortened url associated with the url in param
     * @param url use to creat a shortened url
     * @return the new shortened url associated with the url
     */
    @PostMapping
    public ResponseEntity<String> shortenUrl(@RequestParam(value = "url", defaultValue = "") String url) {
        logger.info("POST Request - shortenUrl : url=%s".formatted(url));
        if (Validator.isValidURL(url)) {
            String result;
            if (shortenedUrlService.exists(url)) {
                Optional<ShortenedUrl> shortenUrl = shortenedUrlService.getShortenedUrl(url);
                result = (shortenUrl.isPresent())? shortenUrl.get().getShortenedUrlValue():"";

            } else {
                Optional<Url> urlObject = urlService.shortenUrl(url);
                result = (urlObject.isPresent())? urlObject.get().getShortenedUrl():"";

            }
            logger.info("POST Request - shortenUrl : result=%s".formatted(result));
            return (!result.isEmpty())?ResponseEntity.ok().body("%s/url-shortener/%s".formatted(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString(), result))
                    :ResponseEntity.noContent().build();
        }

        logger.error("POST Request - shortenUrl : Error: The URL is nit valid");
        return ResponseEntity.badRequest().body("Error : The URL is nit valid");

    }
}
