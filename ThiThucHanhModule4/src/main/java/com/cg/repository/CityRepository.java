package com.cg.repository;

import com.cg.model.City;
import com.cg.model.dto.CityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT NEW com.cg.model.dto.CityDTO(c.id, c.name,c.area, c.population, c.gdp, c.description, c.country) FROM City AS c WHERE c.deleted = false")
      List<CityDTO> findAllUserDTOByDeletedIsFalse();

}
