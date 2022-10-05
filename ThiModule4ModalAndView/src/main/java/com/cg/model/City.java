package com.cg.model;

import com.cg.model.dto.CityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;

    private Long area;

    private Long population;

    private Long gdp;

    private String description;

    /*Khóa ngoại tới id của country*/
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public CityDTO toCityDTO(){
        return new CityDTO()
                .setId(id)
                .setCityName(cityName)
                .setArea(String.valueOf(area))
                .setPopulation(population.toString())
                .setGdp(String.valueOf(gdp))
                .setDescription(description)
                .setCountry(country.toCountryDTO());
    }
}
