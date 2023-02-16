package com.cholley.urlshortener.service.impl;

import com.cholley.urlshortener.entity.ShortenedUrl;
import com.cholley.urlshortener.repository.ShortenedUrlRepository;
import com.cholley.urlshortener.service.ShortenedUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortenedUrlServiceImpl implements ShortenedUrlService {

    @Autowired
    ShortenedUrlRepository shortenedUrlRepository;

    /**
     * Create a new shortened url for the url im param
     * @param shortenedUrl the id of the shortened url
     * @param url the original url
     * @return the new ShortenedUrl key/value created in the db
     */
    @Override
    public Optional<ShortenedUrl> shortenUrl(String shortenedUrl, String url) {
        shortenedUrlRepository.save(new ShortenedUrl(url,shortenedUrl));

        return shortenedUrlRepository.findById(shortenedUrl);
    }

    /**
     * get the shortened url associated with the url in param
     * @param url use to creat the shortened url
     * @return the shortened url associated with the url in param
     */
    @Override
    public Optional<ShortenedUrl> getShortenedUrl(String url) {
        return shortenedUrlRepository.findById(url);
    }

    /**
     * verify if a shortened url exist for the url in param
     * @param url to validate
     * @return boolean
     */
    @Override
    public boolean exists(String url) {
        return shortenedUrlRepository.existsById(url);
    }

}
