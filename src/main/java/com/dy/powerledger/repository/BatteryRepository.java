package com.dy.powerledger.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dy.powerledger.model.Battery;


@Repository
public interface BatteryRepository
	    extends JpaRepository<Battery, Integer> {
	
	@Query("select b from Battery b where (:postalcodeFrom is null OR postalcode>=:postalcodeFrom) "
			+ " and (:postalcodeTo is null OR postalcode<=:postalcodeTo) order by b.name" )
	List<Battery> findByPostalCodeRange(@Param("postalcodeFrom") Integer postalcodeFrom,@Param("postalcodeTo") Integer postalcodeTo);
}
