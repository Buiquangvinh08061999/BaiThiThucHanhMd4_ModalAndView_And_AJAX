package com.cg.service.city;

import com.cg.model.City;
import com.cg.model.Country;
import com.cg.model.dto.CityDTO;
import com.cg.repository.CityRepository;
import com.cg.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService{

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CityDTO> findAllUserDTOByDeletedIsFalse() {
        return cityRepository.findAllUserDTOByDeletedIsFalse();
    }

    @Override
    public List<City> findALl() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City getById(Long id) {
        return cityRepository.getById(id);
    }

    @Override
    public City save(City city) {
        Country country = countryRepository.save(city.getCountry());

        city.setCountry(country);

        return cityRepository.save(city);
    }

    @Override
    public void remove(Long id) {

    }

}
