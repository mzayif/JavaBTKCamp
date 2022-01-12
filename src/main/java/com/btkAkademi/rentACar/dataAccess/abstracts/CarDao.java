package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {
//	@Modifying
//	@Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//	void setUserInfoById(String firstname, String lastname, Integer userId);
//	
//	@Modifying
//	@Query("update Car b set b.name = ?1.name,  where b.id = ?1.id")
//	void updateCarById(Car car);
}
