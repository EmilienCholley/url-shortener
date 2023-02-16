package com.cholley.urlshortener.service.impl;

import com.cholley.urlshortener.entity.Url;
import com.cholley.urlshortener.repository.UrlRepository;
import com.cholley.urlshortener.service.ShortenedUrlService;
import com.cholley.urlshortener.service.UrlService;
import com.cholley.urlshortener.utils.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private ShortenedUrlService shortenedUrlService;

    /**
     * Create a new shortened url for the url im param
     * @param url the original url
     * @return the Url key/value created in the db
     */
    @Override
    public Optional<Url> shortenUrl(String url) {
        String newID;
        synchronized(this) {
            newID = Base62Encoder.base10ToBase62(urlRepository.count());
            Url urlObject = new Url(newID,url);
            urlRepository.save(urlObject);
            shortenedUrlService.shortenUrl(newID,url);
        }
        return urlRepository.findById(newID);
    }

    /**
     * get the original url associated with the shortened url in param
     * @param shortenUrl to get the original url
     * @return the url associated with the shortened url in param
     */
    @Override
    public Optional<Url> getUrl(String shortenUrl) {
        return urlRepository.findById(shortenUrl);
    }



}
