package com.cg.model.dto;

import com.cg.model.City;
import com.cg.model.Country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Accessors(chain = true)
public class CityDTO implements Validator {

    private Long id;

    private String name;
    private String area;

    private String population;

    private String gdp;

    private String description;

    private CountryDTO country;

    public CityDTO(Long id, String name, String area, String population, String gdp, String description, Country country){
        this.id = id;
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
        this.country = country.toCountryDTO();
    }

    public City toCity() {
        return new City()
                .setId(id)
                .setName(name)
                .setArea(area)
                .setPopulation(population)
                .setGdp(gdp)
                .setDescription(description)
                .setCountry(country.toCountry());
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return CityDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CityDTO cityDTO = (CityDTO) target;
        String name = cityDTO.getName();
        String area = cityDTO.getArea();
        String population = cityDTO.getPopulation();
        String gdp = cityDTO.getGdp();
        String description = cityDTO.getDescription();

        if(name.isEmpty()){
            errors.rejectValue("name",  "name.isEmpty" ,"Vui lòng nhập vào nameCity, nameCity không được để trống");
            return;
        }

    }
}
