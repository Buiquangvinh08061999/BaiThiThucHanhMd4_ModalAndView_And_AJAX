package com.cg.repository;

import com.cg.model.Country;
import com.cg.model.dto.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT NEW com.cg.model.dto.CountryDTO (" +
            "c.id, " +
            "c.nameCountry )" +
            "FROM Country AS c ")
    List<CountryDTO> getAllCountryDTO();



    @Query("SELECT NEW com.cg.model.dto.CountryDTO (" +
            "c.id, " +
            "c.nameCountry )" +
            "FROM Country AS c " +
            "WHERE c.id =?1 ")
    Optional<CountryDTO> getAllCountryDTOById(Long id);
    /*Viết ra để hiển thị dữ liệu như eidt, xem thông tin theo id. Nhưng không dùng, Ta dùng hàm tương tự là findById*/
}
