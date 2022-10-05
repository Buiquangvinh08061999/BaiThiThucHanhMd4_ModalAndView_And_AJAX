package com.cg.model;


import com.cg.model.dto.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "countries")
@Accessors(chain = true)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(targetEntity = City.class, mappedBy = "country", fetch = FetchType.EAGER)
    private Set<City> cities;

    public CountryDTO toCountryDTO(){
        return new CountryDTO()
                .setId(id)
                .setName(name);

    }
}
