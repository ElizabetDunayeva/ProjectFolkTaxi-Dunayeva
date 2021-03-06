package com.netcracker.repositories;

import com.netcracker.entities.Journey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, Long> {
}
