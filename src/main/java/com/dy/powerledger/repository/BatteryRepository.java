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
	
	/**
	 * This method is used to get list of batteries based on postal code range
	 * if postalcodeFrom is empty and postalcodeTo is empty then it will return list of all batteries  
	 * if postalcodeFrom is empty and postalcodeTo is not empty then it will return list of all batteries having postal code less than or equal to the postalcodeTo
	 * if postalcodeFrom is not empty and postalcodeTo is empty then it will return list of all batteries having postal code greater than or equal to the postalcodeFrom
	 */
	@Query("select b from Battery b where (:postalcodeFrom is null OR postalcode>=:postalcodeFrom) "
			+ " and (:postalcodeTo is null OR postalcode<=:postalcodeTo) order by b.name" )
	public List<Battery> findByPostalCodeRange(@Param("postalcodeFrom") Integer postalcodeFrom,@Param("postalcodeTo") Integer postalcodeTo);
}
