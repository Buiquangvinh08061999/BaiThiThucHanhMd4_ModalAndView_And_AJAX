package com.cg.service.city;

import com.cg.model.City;
import com.cg.model.dto.CityDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface CityService extends IGeneralService<City> {

    /*Lấy phương thức này lấy ra ở CityRepository*/
    List<CityDTO> getAllCityDTO();

    Optional<CityDTO> getAllCityDTOById(Long id);


    /*phương thức này được hổ trợ đê xóa theo id truyền vào*/
    void deleteById(Long id);


    Boolean exitsByCityName(String name);
}
