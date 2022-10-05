package com.cg.repository;

import com.cg.model.City;
import com.cg.model.dto.CityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface CityRepository extends JpaRepository<City, Long> {

    /*Lấy tất cả các trường ra để hiển thị ở list*/
    @Query("SELECT NEW com.cg.model.dto.CityDTO (" +
            "c.id, " +
            "c.cityName ," +
            "c.area," +
            "c.population," +
            "c.gdp," +
            "c.description," +
            "c.country )" +
            "FROM City AS c ")
    List<CityDTO> getAllCityDTO();


    /*Lấy tất cả các trường ra theo id truyền vào*/
    @Query("SELECT NEW com.cg.model.dto.CityDTO (" +
            "c.id, " +
            "c.cityName ," +
            "c.area," +
            "c.population," +
            "c.gdp," +
            "c.description," +
            "c.country )" +
            "FROM City AS c " +
            "WHERE c.id = ?1")
    Optional<CityDTO> getAllCityDTOById(Long id);



    /*Kiểm tra tên đã bị trùng chưa*/
    Boolean existsByCityName(String name);
}
