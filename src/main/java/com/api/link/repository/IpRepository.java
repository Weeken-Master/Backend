package com.api.link.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.api.link.model.Ip;


public interface IpRepository extends CassandraRepository<Ip, String>{
    
}
