package com.cg.model.dto;

import com.cg.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)

public class CountryDTO {

    private Long id;
    private String name;

    public Country toCountry(){
        return new Country()
                .setId(id)
                .setName(name);
    }

}
