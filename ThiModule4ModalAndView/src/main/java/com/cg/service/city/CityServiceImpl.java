package com.cg.service.city;

import com.cg.model.City;
import com.cg.model.dto.CityDTO;
import com.cg.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    /*Tiêm repo vào để sử dụng */
    @Autowired
    private CityRepository cityRepository;


    @Override
    public List<City> findAll() {
        return null;
    }

    @Override
    public Optional<City> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public City getById(Long id) {
        return null;
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<CityDTO> getAllCityDTO() {
        return cityRepository.getAllCityDTO();
    }

    /*tìm tất các trường theo id truyền vào*/
    @Override
    public Optional<CityDTO> getAllCityDTOById(Long id) {
        return cityRepository.getAllCityDTOById(id);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    /*Kiểm tra trùng tên*/
    @Override
    public Boolean exitsByCityName(String name) {
        return cityRepository.existsByCityName(name);
    }
}
