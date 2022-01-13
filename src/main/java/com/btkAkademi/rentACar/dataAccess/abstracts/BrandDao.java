package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.Optional;

import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.City;
import com.btkAkademi.rentACar.entities.concretes.RentalExtraService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer>{
	Optional<Brand> findByName(String name);
//	@Modifying
//	@Query("update Brand b set b.name = ?1,  where b.id = ?2")
//	void updateBrandNameById(String name, Integer userId);
//	
//	@Modifying
//	@Query("update Brand b set b.name = :brand.name,  where b.id = :brand.id")
//	void updateBrandById(@Param("brand")Brand brand);
//	
}

