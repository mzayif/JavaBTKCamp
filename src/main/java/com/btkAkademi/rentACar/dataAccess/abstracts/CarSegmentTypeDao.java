package com.btkAkademi.rentACar.dataAccess.abstracts;

import com.btkAkademi.rentACar.entities.concretes.CarSegmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarSegmentTypeDao extends JpaRepository<CarSegmentType, Integer> {
    Optional<CarSegmentType> findBySegmentName(String segmentName);
}
