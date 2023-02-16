package com.cholley.urlshortener.repository;

import com.cholley.urlshortener.entity.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<Url, String> { }

