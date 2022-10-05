package com.cg.model;

import com.cg.model.dto.CityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities")
@Accessors(chain = true)

public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String area;

    private String population;

    private String gdp;


    private String description;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public CityDTO toCityDTO(){
        return new CityDTO()
                .setId(id)
                .setName(name)
                .setArea(area)
                .setPopulation(population)
                .setGdp(gdp)
                .setDescription(description)
                .setCountry(country.toCountryDTO());
    }
}
