package com.cholley.urlshortener.repository;

import com.cholley.urlshortener.entity.ShortenedUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenedUrlRepository extends CrudRepository<ShortenedUrl, String> { }

