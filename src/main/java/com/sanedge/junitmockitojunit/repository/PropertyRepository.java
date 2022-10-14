package com.sanedge.junitmockitojunit.repository;

import org.springframework.data.repository.CrudRepository;

import com.sanedge.junitmockitojunit.entity.PropertyEntity;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}
