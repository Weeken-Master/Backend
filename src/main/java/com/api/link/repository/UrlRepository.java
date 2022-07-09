package com.api.link.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.api.link.model.Url;

public interface UrlRepository extends CassandraRepository<Url, String> {
}
