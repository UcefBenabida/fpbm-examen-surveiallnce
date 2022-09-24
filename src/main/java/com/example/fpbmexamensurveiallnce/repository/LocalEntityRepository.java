package com.example.fpbmexamensurveiallnce.repository;

import com.example.fpbmexamensurveiallnce.entities.LocalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("localEntityRepository")
public interface LocalEntityRepository extends CrudRepository<LocalEntity, Long> {
}

