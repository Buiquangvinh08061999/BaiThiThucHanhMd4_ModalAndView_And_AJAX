package com.cg.model.dto;

import com.cg.model.City;
import com.cg.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CityDTO {


    private Long id;

    @NotBlank(message = "Name not empty")
    private String cityName;

    @NotBlank(message = "Area not empty")
    @Min(value = 1, message ="Vui lòng nhập số dương, không được nhập số âm, tối thiểu là 1" )
    @Pattern(regexp = "^[0-9]+$", message = "Area only digit")
    private String area;

    @NotBlank(message = "Population not empty")
    @Min(value = 1, message ="Vui lòng nhập số dương, không được nhập số âm, tối thiểu là 1" )
    @Pattern(regexp = "^[0-9]+$", message = "Population only digit")
    private String population;

    @NotBlank(message = "gdp not empty")
    @Min(value = 1, message ="Vui lòng nhập số dương, không được nhập số âm, tối thiểu là 1" )
    @Pattern(regexp = "^[0-9]+$", message = "GDP only digit")
    private String gdp;

    @NotBlank(message = "description not empty")
    private String description;


    private CountryDTO country;

    /*Thao tác repository ở cityDTO, nhưng các tham số truyền vào phải của City, đúng kiểu các trường bên City*/
    public CityDTO(Long id, String cityName, Long area, Long population, Long gdp, String description, Country country) {
        this.id = id;
        this.cityName = cityName;
        this.area = area.toString();
        this.population = population.toString();
        this.gdp = gdp.toString();
        this.description = description;
        this.country = country.toCountryDTO();
    }

    public City toCity(){
        return new City()
                .setId(id)
                .setCityName(cityName)
                .setArea(Long.parseLong(area))
                .setPopulation(Long.parseLong(population))
                .setGdp(Long.parseLong(gdp))
                .setDescription(description)
                .setCountry(country.toCountry());

    }
}
